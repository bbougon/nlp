package fr.bbougon.nlp.domain;

public interface NLPClient {
    Result processRequest(String text);
}
