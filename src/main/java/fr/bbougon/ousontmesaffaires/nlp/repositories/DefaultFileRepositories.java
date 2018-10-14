package fr.bbougon.ousontmesaffaires.nlp.repositories;

import fr.bbougon.ousontmesaffaires.nlp.Configuration;
import fr.bbougon.ousontmesaffaires.nlp.Configuration.ServerConfiguration;
import fr.bbougon.ousontmesaffaires.nlp.Main;
import fr.bbougon.ousontmesaffaires.nlp.infrastructure.ConfigurationProperties;
import io.undertow.Undertow;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.net.InetAddress;

public class DefaultFileRepositories extends FileRepositories {

    public DefaultFileRepositories(final ConfigurationProperties configurationProperties) {
        this.configurationProperties = configurationProperties;
    }

    @Override
    public FileRepository<ServerConfiguration> getServerConfiguration() {
        return () -> (ServerConfiguration) this::getServerSettings;
    }

    private Configuration.ServerSettings getServerSettings() {

        return new Configuration.ServerSettings() {
            @Override
            public Undertow.Builder getBuilder() {
                try {
                    return Undertow.builder().addHttpListener(getPort(), InetAddress.getLocalHost().getHostAddress());
                } catch (Exception e) {
                    LoggerFactory.getLogger(Main.class).info("Server started with errors on host 'localhost' and port {}: {}", getPort(), e.getMessage());
                    return null;
                }
            }

            @Override
            public int getPort() {
                return configurationProperties.serverPort();
            }

            @Override
            public String nlpVersion() {
                return configurationProperties.getNlpVersion();
            }

            @Override
            public String nlpUsername() {
                return configurationProperties.getNlpUsername();
            }

            @Override
            public String nlpPassword() {
                return configurationProperties.getNlPassword();
            }
        };
    }

    private ConfigurationProperties configurationProperties;
}
