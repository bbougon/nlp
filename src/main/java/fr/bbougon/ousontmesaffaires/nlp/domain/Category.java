package fr.bbougon.ousontmesaffaires.nlp.domain;

import java.math.BigDecimal;

public interface Category {
    String getHierarchy();

    BigDecimal getScore();
}
