package fr.bbougon.nlp;

import fr.bbougon.nlp.infrastructure.NLPConfiguration;

class NLPApplicationForTests extends NLPApplication {

    @Override
    NLPConfiguration getConfiguration() {
        return new NLPConfigurationForTest();
    }

}
