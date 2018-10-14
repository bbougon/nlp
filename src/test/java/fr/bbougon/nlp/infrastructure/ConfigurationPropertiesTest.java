package fr.bbougon.nlp.infrastructure;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

@ExtendWith(WithSystemEnvironment.class)
class ConfigurationPropertiesTest {

    @BeforeEach
    void beforeEach() {
        keys.put("nlp.version", "$NLP_VERSION");
        keys.put("nlp.username", "$NLP_USERNAME");
        keys.put("nlp.password", "$NLP_PASSWORD");
        keys.put("server.port", "$SERVER_PORT");
    }

    @Test
    @DisplayName("can get properties from property file")
    void standardConfiguration() {
        ConfigurationProperties configurationProperties = new ConfigurationProperties(ResourceBundle.getBundle("configuration-for-tests"));

        assertThat(configurationProperties.serverPort()).isEqualTo(8182);
        assertThat(configurationProperties.getNlpUsername()).isEqualTo("user");
        assertThat(configurationProperties.getNlPassword()).isEqualTo("abc");
        assertThat(configurationProperties.getNlpVersion()).isEqualTo("2018-09-21");
    }

    @Test
    @DisplayName("can get properties from environment variables")
    void environmentVariables() {
        ConfigurationProperties configurationProperties = new ConfigurationProperties(keys);

        assertThat(configurationProperties.serverPort()).isEqualTo(Integer.parseInt("6789"));
        assertThat(configurationProperties.getNlpUsername()).isEqualTo("user");
        assertThat(configurationProperties.getNlPassword()).isEqualTo("abc");
        assertThat(configurationProperties.getNlpVersion()).isEqualTo("2018-09-21");
    }

    @Test
    @DisplayName("it throws exception with message when key does not exist")
    void handleNonExistingKey() {
        try {
            ConfigurationProperties configurationProperties = new ConfigurationProperties(Maps.newHashMap());
            configurationProperties.serverPort();
            failBecauseExceptionWasNotThrown(ApplicationConfigurationException.class);
        } catch (ApplicationConfigurationException e) {
            assertThat(e.getMessage()).isEqualTo("Error during application configuration, key 'server.port' not provided!");
        }
    }

    private HashMap<String, String> keys = Maps.newHashMap();
}