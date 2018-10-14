package fr.bbougon.nlp.web.providers;

import com.google.common.io.Resources;
import fr.bbougon.nlp.ResultBuilderForTest;
import fr.bbougon.nlp.domain.Category;
import fr.bbougon.nlp.domain.Concept;
import fr.bbougon.nlp.domain.EntitiesAnalyze;
import fr.bbougon.nlp.domain.Result;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JsonResultMessageBodyWriterTest {

    @Test
    @DisplayName("result is map into json")
    void canWriteResult() throws Exception {
        ResultBuilderForTest resultBuilderForTest = new ResultBuilderForTest();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        new JsonResultMessageBodyWriter().writeTo(resultBuilderForTest, Result.class, null, null, null, null, byteArrayOutputStream);

        assertThat(new String(byteArrayOutputStream.toByteArray())).isEqualTo(IOUtils.toString(Resources.getResource("json/expected-result.json").toURI(), Charset.defaultCharset()));
    }

    @Test
    @DisplayName("can map any result implementation")
    void isWriteable() {
        assertThat(new JsonResultMessageBodyWriter().isWriteable(CustomResult.class, null, null, null)).isTrue();
    }

    private class CustomResult implements CustomResultInterface {
        @Override
        public EntitiesAnalyze getEntitiesAnalysis() {
            return null;
        }

        @Override
        public List<Category> getCategories() {
            return null;
        }

        @Override
        public List<Concept> getConcepts() {
            return null;
        }
    }

    private interface CustomResultInterface extends Result {

    }
}