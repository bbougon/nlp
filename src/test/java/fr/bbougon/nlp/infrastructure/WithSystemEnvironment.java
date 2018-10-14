package fr.bbougon.nlp.infrastructure;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.extension.Extension;

import java.util.Map;

public class WithSystemEnvironment implements Extension {

    public WithSystemEnvironment() {
        SystemEnvironment.SingletonHolder.INSTANCE = new SystemEnvironment() {
            @Override
            String env(final String key) {
                return VARIABLES.get(key);
            }
        };
    }

    private static Map<String, String> VARIABLES = Maps.newHashMap();

    private static final String SERVER_PORT = "6789";
    private static final String NLP_VERSION = "2018-09-21";
    private static final String NLP_USERNAME = "user";
    private static final String NLP_PASSWORD = "abc";

    static {
        VARIABLES.put("SERVER_PORT", SERVER_PORT);
        VARIABLES.put("NLP_VERSION", NLP_VERSION);
        VARIABLES.put("NLP_USERNAME", NLP_USERNAME);
        VARIABLES.put("NLP_PASSWORD", NLP_PASSWORD);
    }

}
