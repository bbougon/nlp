package fr.bbougon.nlp.web.providers;

import com.google.common.collect.Lists;
import fr.bbougon.nlp.web.providers.exceptions.StackTraceElementWithHttpMethodAnnotationWrapper;
import fr.bbougon.nlp.web.providers.exceptions.StackTraceElementWithPathWrapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
public class StandardExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(final Throwable throwable) {
        if (throwable instanceof WebApplicationException) {
            return ((WebApplicationException) throwable).getResponse();
        }
        StringBuilder messageBuilder = new StringBuilder();
        String packageName = "fr.bbougon.nlp.web.resources";
        Lists.newArrayList(throwable.getStackTrace())
                .stream()
                .filter(stackTraceElement -> stackTraceElement.getClassName().contains(packageName))
                .map(StackTraceElementWithPathWrapper::new)
                .map(StackTraceElementWithHttpMethodAnnotationWrapper::new)
                .forEach(element -> {
                    messageBuilder.append(RESOURCE_MESSAGE);
                    messageBuilder.append("'").append(element.getPath().value()).append("'");
                    messageBuilder.append(" (method ").append(element.getMethod().value()).append("): ");
                });
        return Response
                .status(INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(messageBuilder.append(throwable.getMessage()).toString())
                .build();
    }

    private static final String RESOURCE_MESSAGE = "Error has been thrown trying to access resource ";
}
