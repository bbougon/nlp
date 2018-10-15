package fr.bbougon.nlp.web.resources;

import java.math.BigDecimal;

public class TextToAnalyse {
    public TextToAnalyse(final String text, final BigDecimal minimumScore) {
        this.text = text;
        this.minimumScore = minimumScore.doubleValue();
    }

    public String text;
    public Double minimumScore;
}
