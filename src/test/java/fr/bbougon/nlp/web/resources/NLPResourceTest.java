package fr.bbougon.nlp.web.resources;

import fr.bbougon.nlp.domain.RequestBuilderForTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class NLPResourceTest {

    @Test
    @DisplayName("it sends request to nlp service and retrieve result")
    void cenGetNLP() {
        NLPResource nlpResource = new NLPResource();
        nlpResource.request = new RequestBuilderForTest().ibm().build();

        Response response = nlpResource.processRequest("Un texte à analyser");

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
    }
}
