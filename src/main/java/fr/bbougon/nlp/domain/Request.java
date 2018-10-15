package fr.bbougon.nlp.domain;

import com.google.inject.Inject;
import fr.bbougon.nlp.web.resources.TextToAnalyse;

public class Request {

    @Inject
    public Request(final NLPClient client) {
        this.client = client;
    }

    public Result send(final TextToAnalyse textToAnalyse) {
        return client.processRequest(textToAnalyse);
    }

    private final NLPClient client;
}
