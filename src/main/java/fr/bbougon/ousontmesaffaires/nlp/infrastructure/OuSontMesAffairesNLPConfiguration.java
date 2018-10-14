package fr.bbougon.ousontmesaffaires.nlp.infrastructure;

import com.google.inject.AbstractModule;
import fr.bbougon.ousontmesaffaires.nlp.Configuration;
import fr.bbougon.ousontmesaffaires.nlp.infrastructure.module.NLPClientModule;
import fr.bbougon.ousontmesaffaires.nlp.repositories.FileRepositories;

public class OuSontMesAffairesNLPConfiguration extends AbstractModule {

    @Override
    protected void configure() {
        Configuration.ServerSettings settings = FileRepositories.serverConfiguration().get().getSettings();
        install(new NLPClientModule(settings.nlpVersion(), settings.nlpUsername(), settings.nlpPassword()));
    }

}
