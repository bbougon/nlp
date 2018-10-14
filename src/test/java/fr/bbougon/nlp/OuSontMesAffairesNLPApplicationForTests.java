package fr.bbougon.nlp;

import fr.bbougon.nlp.infrastructure.OuSontMesAffairesNLPConfiguration;

class OuSontMesAffairesNLPApplicationForTests extends OuSontMesAffairesNLPApplication {

    @Override
    OuSontMesAffairesNLPConfiguration getConfiguration() {
        return new OuSontMesAffairesNLPConfigurationForTest();
    }

}
