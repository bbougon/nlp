package fr.bbougon.nlp.domain;


import java.util.List;

public interface Result {

    EntitiesAnalyze getEntitiesAnalysis();

    List<Category> getCategories();

    List<Concept> getConcepts();
}
