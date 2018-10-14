package fr.bbougon.nlp.web.resources;

import com.google.inject.Inject;
import fr.bbougon.nlp.domain.Request;
import fr.bbougon.nlp.domain.Result;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("request")
public class NLPResource {

    @POST
    public Response processRequest(final String textToAnalyse) {
        Result result = request.send(textToAnalyse);
        return Response.ok().entity(result).build();
    }

    @Inject
    public Request request;
}
