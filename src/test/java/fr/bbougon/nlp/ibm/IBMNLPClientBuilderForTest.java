package fr.bbougon.nlp.ibm;

import fr.bbougon.nlp.domain.NLPClient;

public class IBMNLPClientBuilderForTest {
    public NLPClient build() {
        return new IBMNLPClient(new NaturalLanguageUnderstandingForTests());
    }
}
