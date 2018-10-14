package fr.bbougon.ousontmesaffaires.nlp.repositories;

import fr.bbougon.ousontmesaffaires.nlp.Configuration.ServerConfiguration;
import fr.bbougon.ousontmesaffaires.nlp.infrastructure.ConfigurationProperties;

public abstract class FileRepositories {

    public static void initialise(final FileRepositories fileRepositories) {
        SingletonHolder.INSTANCE = fileRepositories;
    }

    public static FileRepository<ServerConfiguration> serverConfiguration() {
        return SingletonHolder.INSTANCE.getServerConfiguration();
    }

    public abstract FileRepository<ServerConfiguration> getServerConfiguration();

    private static class SingletonHolder {
        static FileRepositories INSTANCE = new DefaultFileRepositories(new ConfigurationProperties());
    }
}
