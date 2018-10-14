package fr.bbougon.nlp.infrastructure;

import com.google.inject.AbstractModule;
import fr.bbougon.nlp.Configuration;
import fr.bbougon.nlp.infrastructure.module.NLPClientModule;
import fr.bbougon.nlp.repositories.FileRepositories;

public class OuSontMesAffairesNLPConfiguration extends AbstractModule {

    @Override
    protected void configure() {
        Configuration.ServerSettings settings = FileRepositories.serverConfiguration().get().getSettings();
        install(new NLPClientModule(settings.nlpVersion(), settings.nlpUsername(), settings.nlpPassword()));
    }

}
