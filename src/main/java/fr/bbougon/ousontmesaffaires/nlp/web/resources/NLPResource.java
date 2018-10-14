package fr.bbougon.ousontmesaffaires.nlp.web.resources;

import fr.bbougon.ousontmesaffaires.nlp.domain.Request;

import javax.ws.rs.core.Response;

public class NLPResource {
    public Response processRequest(final String textToAnalyse) {
        return Response.ok().build();
    }

    public Request request;
}
