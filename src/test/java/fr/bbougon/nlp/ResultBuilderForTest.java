package fr.bbougon.nlp;

import com.google.common.collect.Lists;
import fr.bbougon.nlp.domain.*;

import java.util.List;

public class ResultBuilderForTest implements Result {

    public ResultBuilderForTest() {
        entitiesAnalyze = new EntitiesAnalyzeBuilderForTest().build();
        categories = Lists.newArrayList(new CategoryBuilderForTest().build());
        concepts = Lists.newArrayList(new ConceptBuilderForTest().build());
    }

    @Override
    public EntitiesAnalyze getEntitiesAnalysis() {
        return entitiesAnalyze;
    }

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public List<Concept> getConcepts() {
        return concepts;
    }

    private EntitiesAnalyze entitiesAnalyze;
    private List<Category> categories;
    private List<Concept> concepts;
}
