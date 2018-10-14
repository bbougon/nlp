package fr.bbougon.nlp.domain;

import java.math.BigDecimal;

public interface Category {
    String getHierarchy();

    BigDecimal getScore();
}
