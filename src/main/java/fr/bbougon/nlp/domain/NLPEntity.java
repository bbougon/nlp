package fr.bbougon.nlp.domain;

import java.math.BigDecimal;

public interface NLPEntity {
    String getName();
    String getType();
    BigDecimal getRelevance();
}
