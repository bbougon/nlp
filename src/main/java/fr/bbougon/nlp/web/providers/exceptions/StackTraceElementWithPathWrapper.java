package fr.bbougon.nlp.web.providers.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Path;

public class StackTraceElementWithPathWrapper {

    public StackTraceElementWithPathWrapper(final StackTraceElement stackTraceElement) {
        try {
            this.stackTraceElement = stackTraceElement;
            this.path = Class.forName(stackTraceElement.getClassName()).getAnnotation(Path.class);
        } catch (ClassNotFoundException e) {
            LOGGER.debug(e.getMessage());
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
    private static final Logger LOGGER = LoggerFactory.getLogger(StackTraceElementWithPathWrapper.class);
}
