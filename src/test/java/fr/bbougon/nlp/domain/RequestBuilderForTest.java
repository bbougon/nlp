package fr.bbougon.nlp.domain;

import fr.bbougon.nlp.ibm.IBMNLPClientBuilderForTest;

public class RequestBuilderForTest {

    public RequestBuilderForTest ibm() {
        nlpClient = new IBMNLPClientBuilderForTest().build();
        return this;
    }

    public Request build() {
        return new Request(nlpClient);
    }

    private NLPClient nlpClient;
}
