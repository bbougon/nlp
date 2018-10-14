package fr.bbougon.nlp.web.providers;

import fr.bbougon.nlp.web.resources.DummyResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static org.assertj.core.api.Assertions.assertThat;

class StandardExceptionMapperTest {

    @Test
    @DisplayName("it returns a bad request if exception is a WebApplicationException")
    void handleWebApplicationException() {
        StandardExceptionMapper standardExceptionMapper = new StandardExceptionMapper();

        Response response = standardExceptionMapper.toResponse(new BadRequestException());

        assertThat(response.getStatus()).isEqualTo(BAD_REQUEST.getStatusCode());
    }

    @Test
    @DisplayName("it returns a bad request when an exception is thrown from package fr.bbougon.nlp.web.ressources and annotated Path and HttpMethod")
    void handleExceptionFromWebResourcesPackage() {
        StandardExceptionMapper standardExceptionMapper = new StandardExceptionMapper();

        Response response = standardExceptionMapper.toResponse(new DummyResource().exception());

        assertThat(response.getStatus()).isEqualTo(INTERNAL_SERVER_ERROR.getStatusCode());
        assertThat(response.getEntity()).isEqualTo("Error has been thrown trying to access resource 'a-path' (method GET): an exception");
    }
}