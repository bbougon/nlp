package fr.bbougon.nlp.web.resources;

import fr.bbougon.nlp.domain.Request;
import fr.bbougon.nlp.domain.Result;

import javax.ws.rs.core.Response;

public class NLPResource {

    public Response processRequest(final String textToAnalyse) {
        Result result = request.send(textToAnalyse);
        return Response.ok().entity(result).build();
    }

    public Request request;
}
