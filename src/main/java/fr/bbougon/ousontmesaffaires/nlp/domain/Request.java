package fr.bbougon.ousontmesaffaires.nlp.domain;

public class Request {

    Request(final NLPClient client) {
        this.client = client;
    }

    public Result send(final String text) {
        return client.processRequest(text);
    }

    private final NLPClient client;
}
