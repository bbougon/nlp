package fr.bbougon.nlp.web.resources;

import com.google.common.io.Resources;
import fr.bbougon.nlp.WithEmbeddedServerExtension;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(WithEmbeddedServerExtension.class)
class NLPResourceIntegrationTest {

    @Test
    @DisplayName("resource is mapped correctly and return a JSON payload")
    void canReachResource() throws Exception {
        Response response = ClientBuilder.newClient()
                .target("http://localhost:17001")
                .path("request")
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json("{\"text\":\"un texte à analyser\"}"));

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(response.readEntity(String.class)).isEqualTo(IOUtils.toString(Resources.getResource("json/expected-result.json").toURI(), Charset.defaultCharset()));
    }
}