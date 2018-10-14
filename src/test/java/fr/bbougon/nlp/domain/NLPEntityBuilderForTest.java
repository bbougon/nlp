package fr.bbougon.nlp.domain;

import java.math.BigDecimal;

public class NLPEntityBuilderForTest {
    public NLPEntity build() {
        return new NLPEntity() {
            @Override
            public String getName() {
                return "name";
            }

            @Override
            public String getType() {
                return "type";
            }

            @Override
            public BigDecimal getRelevance() {
                return BigDecimal.TEN;
            }
        };
    }
}
