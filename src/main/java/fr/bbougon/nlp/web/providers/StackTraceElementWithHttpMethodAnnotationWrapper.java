package fr.bbougon.nlp.web.providers;

import javax.ws.rs.HttpMethod;
import java.lang.reflect.AccessibleObject;
import java.util.Arrays;
import java.util.Optional;

class StackTraceElementWithHttpMethodAnnotationWrapper extends StackTraceElementWithPathWrapper {

    private StackTraceElementWithHttpMethodAnnotationWrapper(final StackTraceElementWithPathWrapper stackTraceElementWithPathWrapper, final HttpMethod method) {
        super(stackTraceElementWithPathWrapper.getStackTraceElement(), stackTraceElementWithPathWrapper.getPath());
        this.method = method;
    }

    static Optional<StackTraceElementWithHttpMethodAnnotationWrapper> getStackTraceElementWithHttpMethodAnnotationWrapper(final StackTraceElementWithPathWrapper stackTraceElementWithPathWrapper) {
        try {
            return Arrays.stream(Class.forName(stackTraceElementWithPathWrapper.getStackTraceElement().getClassName()).getMethods())
                    .filter(method -> method.getName().equals(stackTraceElementWithPathWrapper.getStackTraceElement().getMethodName()))
                    .map(AccessibleObject::getAnnotations)
                    .map(AnnotationWrapper::new)
                    .map(AnnotationWrapper::getHttpMethod)
                    .map(Optional::get)
                    .findFirst()
                    .map(httpMethod -> new StackTraceElementWithHttpMethodAnnotationWrapper(stackTraceElementWithPathWrapper, httpMethod));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public HttpMethod getMethod() {
        return method;
    }

    private final HttpMethod method;
}
