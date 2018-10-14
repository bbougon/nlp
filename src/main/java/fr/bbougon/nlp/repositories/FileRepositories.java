package fr.bbougon.nlp.repositories;

import fr.bbougon.nlp.Configuration;
import fr.bbougon.nlp.infrastructure.ConfigurationProperties;

public abstract class FileRepositories {

    public static void initialise(final FileRepositories fileRepositories) {
        SingletonHolder.INSTANCE = fileRepositories;
    }

    public static FileRepository<Configuration.ServerConfiguration> serverConfiguration() {
        return SingletonHolder.INSTANCE.getServerConfiguration();
    }

    public abstract FileRepository<Configuration.ServerConfiguration> getServerConfiguration();

    private static class SingletonHolder {
        static FileRepositories INSTANCE = new DefaultFileRepositories(new ConfigurationProperties());
    }
}
