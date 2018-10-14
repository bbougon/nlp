package fr.bbougon.nlp;

import fr.bbougon.nlp.domain.NLPClient;
import fr.bbougon.nlp.infrastructure.NLPConfiguration;

class NLPConfigurationForTest extends NLPConfiguration {

    @Override
    protected void configure() {
        bind(NLPClient.class).to(NLPClientForTest.class);
    }
}
