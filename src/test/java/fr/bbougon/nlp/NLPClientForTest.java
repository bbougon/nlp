package fr.bbougon.nlp;

import fr.bbougon.nlp.domain.NLPClient;
import fr.bbougon.nlp.domain.Result;
import fr.bbougon.nlp.web.resources.TextToAnalyse;

class NLPClientForTest implements NLPClient {

    @Override
    public Result processRequest(final TextToAnalyse textToAnalyse) {
        return new ResultBuilderForTest();
    }
}
