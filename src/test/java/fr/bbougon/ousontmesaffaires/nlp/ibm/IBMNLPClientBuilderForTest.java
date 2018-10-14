package fr.bbougon.ousontmesaffaires.nlp.ibm;

import fr.bbougon.ousontmesaffaires.nlp.domain.NLPClient;

public class IBMNLPClientBuilderForTest {
    public NLPClient build() {
        return new IBMNLPClient(new NaturalLanguageUnderstandingForTests());
    }
}
