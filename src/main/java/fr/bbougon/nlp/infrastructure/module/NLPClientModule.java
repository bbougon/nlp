package fr.bbougon.nlp.infrastructure.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import fr.bbougon.nlp.domain.NLPClient;
import fr.bbougon.nlp.domain.NLPSettings;
import fr.bbougon.nlp.ibm.IBMNLPClient;

public class NLPClientModule extends AbstractModule {

    public NLPClientModule(final String nlpVersion, final String nlpUsername, final String nlpPassword) {
        this.settings = new NLPSettings(nlpVersion, nlpUsername, nlpPassword);
    }

    @Override
    protected void configure() {
        bind(NLPClient.class).to(IBMNLPClient.class);
    }

    @Provides
    public NLPSettings settings() {
        return settings;
    }

    private final NLPSettings settings;
}
