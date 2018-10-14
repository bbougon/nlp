package fr.bbougon.nlp.domain;

import java.util.List;

public interface EntitiesAnalyze {

    List<NLPEntity> getEntities();

    String getLanguage();
}
