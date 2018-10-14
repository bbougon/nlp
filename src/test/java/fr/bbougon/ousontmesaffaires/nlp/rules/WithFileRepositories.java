package fr.bbougon.ousontmesaffaires.nlp.rules;

import fr.bbougon.ousontmesaffaires.nlp.Configuration;
import fr.bbougon.ousontmesaffaires.nlp.repositories.FileRepositories;
import fr.bbougon.ousontmesaffaires.nlp.repositories.FileRepository;
import io.undertow.Undertow;
import org.junit.jupiter.api.extension.Extension;

public class WithFileRepositories implements Extension {

    public WithFileRepositories() {
        FileRepositories.initialise(new FileRepositories() {
            @Override
            public FileRepository<Configuration.ServerConfiguration> getServerConfiguration() {
                return () -> (Configuration.ServerConfiguration) () -> new Configuration.ServerSettings() {
                    @Override
                    public Undertow.Builder getBuilder() {
                        return Undertow.builder().addHttpListener(getPort(), "localhost");
                    }

                    @Override
                    public int getPort() {
                        return 17001;
                    }

                    @Override
                    public String nlpVersion() {
                        return "version";
                    }

                    @Override
                    public String nlpUsername() {
                        return "username";
                    }

                    @Override
                    public String nlpPassword() {
                        return "password";
                    }
                };
            }

        });
    }
}
