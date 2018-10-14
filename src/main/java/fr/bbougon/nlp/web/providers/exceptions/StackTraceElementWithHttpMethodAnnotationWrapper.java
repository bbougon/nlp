package fr.bbougon.nlp.web.providers.exceptions;

import javax.ws.rs.HttpMethod;
import java.lang.reflect.AccessibleObject;
import java.util.Arrays;
import java.util.Optional;

public class StackTraceElementWithHttpMethodAnnotationWrapper extends StackTraceElementWithPathWrapper {

    public StackTraceElementWithHttpMethodAnnotationWrapper(final StackTraceElementWithPathWrapper stackTraceElementWithPathWrapper) {
        super(stackTraceElementWithPathWrapper.getStackTraceElement(), stackTraceElementWithPathWrapper.getPath());
        try {
            Arrays.stream(Class.forName(stackTraceElementWithPathWrapper.getStackTraceElement().getClassName()).getMethods())
                    .filter(method -> method.getName().equals(stackTraceElementWithPathWrapper.getStackTraceElement().getMethodName()))
                    .map(AccessibleObject::getAnnotations)
                    .map(AnnotationWrapper::new)
                    .map(AnnotationWrapper::getHttpMethod)
                    .map(Optional::get)
                    .findFirst()
                    .ifPresent(httpMethod -> this.method = httpMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HttpMethod getMethod() {
        return method;
    }

    private HttpMethod method;
}
