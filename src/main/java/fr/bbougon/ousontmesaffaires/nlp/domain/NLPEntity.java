package fr.bbougon.ousontmesaffaires.nlp.domain;

import java.math.BigDecimal;

public interface NLPEntity {
    String getName();
    String getType();
    BigDecimal getRelevance();
}
