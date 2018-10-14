package fr.bbougon.ousontmesaffaires.nlp.infrastructure.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import fr.bbougon.ousontmesaffaires.nlp.domain.NLPSettings;

public class NLPClientModule extends AbstractModule {

    public NLPClientModule(final String nlpVersion, final String nlpUsername, final String nlpPassword) {
        this.settings = new NLPSettings(nlpVersion, nlpUsername, nlpPassword);
    }

    @Override
    protected void configure() {
    }

    @Provides
    public NLPSettings settings() {
        return settings;
    }

    private final NLPSettings settings;
}
