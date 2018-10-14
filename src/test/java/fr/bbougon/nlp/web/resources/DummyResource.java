package fr.bbougon.nlp.web.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("a-path")
public class DummyResource {

    @GET
    public Throwable exception() {
        return new RuntimeException("an exception");
    }
}
