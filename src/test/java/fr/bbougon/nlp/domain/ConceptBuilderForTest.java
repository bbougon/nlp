package fr.bbougon.nlp.domain;

import java.math.BigDecimal;

public class ConceptBuilderForTest {
    public Concept build() {
        return new Concept() {
            @Override
            public String getName() {
                return "name";
            }

            @Override
            public BigDecimal getScore() {
                return BigDecimal.TEN;
            }
        };
    }
}
