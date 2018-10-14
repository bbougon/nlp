package fr.bbougon.ousontmesaffaires.nlp.domain;

public interface NLPClient {
    Result processRequest(String text);
}
