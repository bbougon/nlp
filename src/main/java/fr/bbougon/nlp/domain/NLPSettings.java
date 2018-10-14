package fr.bbougon.nlp.domain;

import com.google.inject.Inject;

public class NLPSettings {

    @Inject
    public NLPSettings(final String nlpVersion, final String nlpUsername, final String nlpPassword) {
        version = nlpVersion;
        username = nlpUsername;
        password = nlpPassword;
    }

    public String getVersion() {
        return version;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private String version;
    private String username;
    private String password;
}
