package fr.bbougon.nlp;

import fr.bbougon.nlp.repositories.FileRepositories;
import io.undertow.Undertow;

public class Configuration {

    public static ServerConfiguration getServerConfiguration() {
        return FileRepositories.serverConfiguration().get();
    }

    @FunctionalInterface
    public interface ServerConfiguration {
        ServerSettings getSettings();
    }

    public interface ServerSettings {
        Undertow.Builder getBuilder();

        int getPort();

        String nlpVersion();

        String nlpUsername();

        String nlpPassword();
    }

}
