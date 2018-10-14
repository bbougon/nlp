package fr.bbougon.nlp.domain;

import java.math.BigDecimal;

public class CategoryBuilderForTest {
    public Category build() {
        return new Category() {
            @Override
            public String getHierarchy() {
                return "/a/hierrachy";
            }

            @Override
            public BigDecimal getScore() {
                return BigDecimal.TEN;
            }
        };
    }
}
