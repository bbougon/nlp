package fr.bbougon.ousontmesaffaires.nlp.domain;

import java.math.BigDecimal;

public interface Concept {
    String getName();

    BigDecimal getScore();
}
