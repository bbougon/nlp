package fr.bbougon.nlp.ibm;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;

public class EntitiesResultBuilderFortTest {
    public EntitiesResultBuilderFortTest withText(final String text) {
        this.text = text;
        return this;
    }

    public EntitiesResultBuilderFortTest withType(final String type) {
        this.type = type;
        return this;
    }

    public EntitiesResultBuilderFortTest withRelevance(final Double relevance) {
        this.relevance = relevance;
        return this;
    }

    public EntitiesResult build() {
        EntitiesResult entitiesResult = new EntitiesResult() {
            @Override
            public String getType() {
                return type;
            }

            @Override
            public String getText() {
                return text;
            }

            @Override
            public Double getRelevance() {
                return relevance;
            }
        };
        return entitiesResult;
    }

    private String text;
    private String type;
    private Double relevance;
}
