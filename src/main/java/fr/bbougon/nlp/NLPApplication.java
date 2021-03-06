package fr.bbougon.nlp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import fr.bbougon.nlp.infrastructure.NLPConfiguration;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.Provider;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationPath("/")
public class NLPApplication extends Application {

    NLPApplication() {
        injector = Guice.createInjector(Stage.DEVELOPMENT, getConfiguration());
    }

    NLPConfiguration getConfiguration() {
        return new NLPConfiguration();
    }

    @Override
    public Set<Object> getSingletons() {
        CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        corsFilter.setExposedHeaders("Cache-Control, Content-Language, Content-Type, Expires, Last-Modified, Pragma, Location");
        corsFilter.setAllowedHeaders("Location, Content-Type");
        Set<Object> instances = scanPackages().stream().map(injector::getInstance).collect(Collectors.toSet());
        instances.add(corsFilter);
        return instances;
    }

    private Set<Class<?>> scanPackages() {
        Set<Class<?>> result = scanPackages("fr.bbougon.nlp.web", Path.class);
        result.addAll(scanPackages("fr.bbougon.nlp.web.providers", Provider.class));
        return result;
    }

    private Set<Class<?>> scanPackages(final String resourcesPackageName, final Class<?> annotation) {
        try (ScanResult scanResult = new ClassGraph()
                .whitelistPackages(resourcesPackageName)
                .enableAllInfo()
                .scan()) {
            return scanResult.getClassesWithAnnotation(annotation.getCanonicalName())
                    .stream()
                    .map(ClassInfo::loadClass)
                    .collect(Collectors.toSet());
        }
    }

    private final Injector injector;
}
