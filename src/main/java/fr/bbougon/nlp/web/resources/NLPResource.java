package fr.bbougon.nlp.web.resources;

import fr.bbougon.nlp.domain.Request;

import javax.ws.rs.core.Response;

public class NLPResource {
    public Response processRequest(final String textToAnalyse) {
        return Response.ok().build();
    }

    public Request request;
}
