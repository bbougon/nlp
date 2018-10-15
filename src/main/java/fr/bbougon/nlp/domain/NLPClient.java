package fr.bbougon.nlp.domain;

import fr.bbougon.nlp.web.resources.TextToAnalyse;

public interface NLPClient {
    Result processRequest(TextToAnalyse textToAnalyse);
}
