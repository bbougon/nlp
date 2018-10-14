package fr.bbougon.nlp.domain;

import com.google.inject.Inject;

public class Request {

    @Inject
    public Request(final NLPClient client) {
        this.client = client;
    }

    public Result send(final String text) {
        return client.processRequest(text);
    }

    private final NLPClient client;
}
