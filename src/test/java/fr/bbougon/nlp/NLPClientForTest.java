package fr.bbougon.nlp;

import fr.bbougon.nlp.domain.NLPClient;
import fr.bbougon.nlp.domain.Result;

class NLPClientForTest implements NLPClient {

    @Override
    public Result processRequest(final String text) {
        return new ResultBuilderForTest();
    }
}
