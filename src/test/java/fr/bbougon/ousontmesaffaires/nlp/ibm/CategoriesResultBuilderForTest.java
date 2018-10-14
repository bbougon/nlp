package fr.bbougon.ousontmesaffaires.nlp.ibm;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesResult;

public class CategoriesResultBuilderForTest {
    public CategoriesResultBuilderForTest withLabel(final String label) {
        this.label = label;
        return this;
    }

    public CategoriesResultBuilderForTest withScore(final Double score) {
        this.score = score;
        return this;
    }

    public CategoriesResult build() {
        return new CategoriesResult() {
            @Override
            public String getLabel() {
                return label;
            }

            @Override
            public Double getScore() {
                return score;
            }
        };
    }

    private String label;
    private Double score;
}
