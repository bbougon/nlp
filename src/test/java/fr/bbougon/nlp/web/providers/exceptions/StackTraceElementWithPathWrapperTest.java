package fr.bbougon.nlp.web.providers.exceptions;

import ch.qos.logback.classic.Level;
import fr.bbougon.nlp.test.utils.TestAppender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StackTraceElementWithPathWrapperTest {

    @Test
    @DisplayName("it logs an exception at debug level")
    void log() {
        new StackTraceElementWithPathWrapper(new StackTraceElement("class", "method", "file", 1));

        assertThat(TestAppender.hasMessageInLevel(Level.DEBUG, "class")).isTrue();
    }
}