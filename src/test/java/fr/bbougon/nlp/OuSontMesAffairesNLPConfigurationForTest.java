package fr.bbougon.nlp;

import fr.bbougon.nlp.domain.NLPClient;
import fr.bbougon.nlp.infrastructure.OuSontMesAffairesNLPConfiguration;

class OuSontMesAffairesNLPConfigurationForTest extends OuSontMesAffairesNLPConfiguration {

    @Override
    protected void configure() {
        bind(NLPClient.class).to(NLPClientForTest.class);
    }
}
