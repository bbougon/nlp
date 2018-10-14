package fr.bbougon.ousontmesaffaires.nlp.ibm;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsResult;

public class ConceptsResultBuilderForTest {

    public ConceptsResult build() {
        return new ConceptsResult() {
            @Override
            public String getText() {
                return text;
            }

            @Override
            public Double getRelevance() {
                return relevance;
            }

            @Override
            public String getDbpediaResource() {
                return dbpediaResource;
            }
        };
    }

    public ConceptsResultBuilderForTest withText(final String text) {
        this.text = text;
        return this;
    }

    public ConceptsResultBuilderForTest withRelevance(final Double relevance) {
        this.relevance = relevance;
        return this;
    }

    public ConceptsResultBuilderForTest withDbpediaResource(final String dbpediaResource) {
        this.dbpediaResource = dbpediaResource;
        return this;
    }

    private String text;
    private Double relevance;
    private String dbpediaResource;
}
