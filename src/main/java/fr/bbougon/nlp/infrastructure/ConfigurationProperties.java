package fr.bbougon.nlp.infrastructure;

import com.google.common.collect.Maps;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ConfigurationProperties {

    public ConfigurationProperties() {
        this(ResourceBundle.getBundle("configuration"));
    }

    ConfigurationProperties(final HashMap<String, String> keys) {
        this.keys = keys;
    }

    ConfigurationProperties(final ResourceBundle bundle) {
        this.keys = Maps.newHashMap();
        bundle.keySet().forEach(key -> keys.put(key, bundle.getString(key)));
    }

    public int serverPort() {
        LOGGER.debug("Configuring server port {}", getKeyValue(SERVER_PORT));
        return Integer.parseInt(getKeyValue(SERVER_PORT));
    }

    private String getKeyValue(final String key) {
        checkKeyExistence(key);
        if (isEnvironmentVariable(key)) {
            return SystemEnvironment.getEnv(keys.get(key).substring(1));
        }
        return keys.get(key);
    }

    private void checkKeyExistence(final String key) {
        if (keys.get(key) == null) {
            throw new ApplicationConfigurationException(String.format("Error during application configuration, key '%s' not provided!", key));
        }
    }


    private boolean isEnvironmentVariable(final String key) {
        return keys.get(key).startsWith(LINUX_ENV_PREFIX);
    }

    public String getNlpVersion() {
        return getKeyValue("nlp.version");
    }

    public String getNlpUsername() {
        return getKeyValue("nlp.username");
    }

    public String getNlPassword() {
        return getKeyValue("nlp.password");
    }

    private String nlpVersion;
    private String nlpUsername;
    private String nlPassword;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ConfigurationProperties.class);
    private final Map<String, String> keys;
    private static final String LINUX_ENV_PREFIX = "$";
    private static final String SERVER_PORT = "server.port";
}
