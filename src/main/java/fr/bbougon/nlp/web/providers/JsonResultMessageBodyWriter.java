package fr.bbougon.nlp.web.providers;

import com.google.gson.GsonBuilder;
import fr.bbougon.nlp.domain.Result;
import fr.bbougon.nlp.web.providers.mappers.json.ResultMapper;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;

@Provider
@Produces("application/json")
public class JsonResultMessageBodyWriter implements MessageBodyWriter<Result> {

    @Override
    public boolean isWriteable(final Class<?> type, final Type genericType, final Annotation[] annotations, final MediaType mediaType) {
        return hasResultInterface(type);
    }

    private boolean hasResultInterface(final Class<?> type) {
        return Arrays.stream(type.getInterfaces()).anyMatch(aClass -> aClass == Result.class || hasResultInterface(aClass));
    }

    @Override
    public long getSize(final Result result, final Class<?> type, final Type genericType, final Annotation[] annotations, final MediaType mediaType) {
        return 0;
    }

    @Override
    public void writeTo(final Result result, final Class<?> type, final Type genericType, final Annotation[] annotations, final MediaType mediaType, final MultivaluedMap<String, Object> httpHeaders, final OutputStream entityStream) throws IOException, WebApplicationException {
        entityStream.write(new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(new ResultMapper().map(result))
                .getBytes());
    }

}
