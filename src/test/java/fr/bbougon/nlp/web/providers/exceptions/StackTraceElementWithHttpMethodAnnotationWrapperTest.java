package fr.bbougon.nlp.web.providers.exceptions;

import ch.qos.logback.classic.Level;
import fr.bbougon.nlp.test.utils.TestAppender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class StackTraceElementWithHttpMethodAnnotationWrapperTest {

    @Test
    @DisplayName("it logs exceptions at debug level")
    void log() {
        new StackTraceElementWithHttpMethodAnnotationWrapper(new StackTraceElementWithPathWrapper(new StackTraceElement("class", "method", "file", 1), mock(Path.class)));

        assertThat(TestAppender.hasMessageInLevel(Level.DEBUG, "class")).isTrue();
    }
}