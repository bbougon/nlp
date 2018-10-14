package fr.bbougon.nlp.web.providers.exceptions;

import javax.ws.rs.Path;

public class StackTraceElementWithPathWrapper {

    public StackTraceElementWithPathWrapper(final StackTraceElement stackTraceElement) {
        try {
            this.stackTraceElement = stackTraceElement;
            this.path = Class.forName(stackTraceElement.getClassName()).getAnnotation(Path.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    StackTraceElementWithPathWrapper(final StackTraceElement stackTraceElement, final Path path) {
        this.stackTraceElement = stackTraceElement;
        this.path = path;
    }

    public StackTraceElement getStackTraceElement() {
        return stackTraceElement;
    }

    public Path getPath() {
        return path;
    }

    private StackTraceElement stackTraceElement;
    private Path path;
}
