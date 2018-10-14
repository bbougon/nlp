package fr.bbougon.nlp.web.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;

class StandardExceptionMapperTest {

    @Test
    @DisplayName("it returns a bad request if exception is a WebApplicationException")
    public void handleWebApplicationException() {
        StandardExceptionMapper standardExceptionMapper = new StandardExceptionMapper();

        Response response = standardExceptionMapper.toResponse(new BadRequestException());

        assertThat(response.getStatus()).isEqualTo(BAD_REQUEST.getStatusCode());
    }

}