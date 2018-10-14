package fr.bbougon.ousontmesaffaires.nlp;

import fr.bbougon.ousontmesaffaires.nlp.rules.WithFileRepositories;
import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;

@ExtendWith(WithFileRepositories.class)
public class WithEmbeddedServer implements Extension {

    @BeforeAll
    public void before() {
        start();
    }

    @AfterAll
    public void after() {
        server.stop();
    }

    private void start() {
        Configuration.ServerConfiguration configuration = Configuration.getServerConfiguration();
        server = new UndertowJaxrsServer();
        Undertow.Builder serverConfiguration = Undertow.builder().addHttpListener(configuration.getSettings().getPort(), "localhost");
        server.start(serverConfiguration);
    }

    private UndertowJaxrsServer server;
}
