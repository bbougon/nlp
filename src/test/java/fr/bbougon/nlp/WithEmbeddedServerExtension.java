package fr.bbougon.nlp;

import fr.bbougon.nlp.rules.WithFileRepositories;
import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

@ExtendWith(WithFileRepositories.class)
public class WithEmbeddedServerExtension implements AfterEachCallback, BeforeEachCallback {

    private void start() {
        Configuration.ServerConfiguration configuration = Configuration.getServerConfiguration();
        server = new UndertowJaxrsServer();
        server.deploy(new NLPApplicationForTests());
        Undertow.Builder serverConfiguration = Undertow.builder().addHttpListener(configuration.getSettings().getPort(), "localhost");
        server.start(serverConfiguration);
    }

    @Override
    public void afterEach(final ExtensionContext extensionContext) {
        server.stop();
    }

    @Override
    public void beforeEach(final ExtensionContext extensionContext) {
        new WithFileRepositories().beforeEach(extensionContext);
        start();
    }

    private UndertowJaxrsServer server;
}
