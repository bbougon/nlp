package fr.bbougon.nlp.web.providers;

import javax.ws.rs.Path;
import java.util.Optional;

class StackTraceElementWithPathWrapper {

    StackTraceElementWithPathWrapper(final StackTraceElement stackTraceElement, final Path path) {
        this.stackTraceElement = stackTraceElement;
        this.path = path;
    }

    static Optional<StackTraceElementWithPathWrapper> getStackTraceElementWithPathWrapper(final StackTraceElement stackTraceElement) {
        try {
            Path path = Class.forName(stackTraceElement.getClassName()).getAnnotation(Path.class);
            if (path != null) {
                return Optional.of(new StackTraceElementWithPathWrapper(stackTraceElement, path));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public StackTraceElement getStackTraceElement() {
        return stackTraceElement;
    }

    public Path getPath() {
        return path;
    }

    private final StackTraceElement stackTraceElement;
    private final Path path;
}
