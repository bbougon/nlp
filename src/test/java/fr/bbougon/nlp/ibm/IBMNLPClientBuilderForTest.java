package fr.bbougon.nlp.ibm;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;

import java.util.List;

public class IBMNLPClientBuilderForTest {

    public IBMNLPClientBuilderForTest withConceptsResult(final List<ConceptsResult> conceptsResults) {
        this.conceptsResults = conceptsResults;
        return this;
    }

    public IBMNLPClientBuilderForTest withCategoriesResult(final List<CategoriesResult> categoriesResults) {
        this.categoriesResults = categoriesResults;
        return this;
    }

    public IBMNLPClientBuilderForTest withEntitiesResult(final List<EntitiesResult> entitiesResults) {
        this.entitiesResults = entitiesResults;
        return this;
    }

    public IBMNLPClient build() {
        return new IBMNLPClient(new NaturalLanguageUnderstandingForTests(entitiesResults, categoriesResults, conceptsResults));
    }

    private List<EntitiesResult> entitiesResults;
    private List<CategoriesResult> categoriesResults;
    private List<ConceptsResult> conceptsResults;
}
