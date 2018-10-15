package fr.bbougon.nlp.web.resources;

import com.google.common.collect.Lists;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;
import fr.bbougon.nlp.domain.Request;
import fr.bbougon.nlp.domain.Result;
import fr.bbougon.nlp.ibm.CategoriesResultBuilderForTest;
import fr.bbougon.nlp.ibm.ConceptsResultBuilderForTest;
import fr.bbougon.nlp.ibm.EntitiesResultBuilderFortTest;
import fr.bbougon.nlp.ibm.IBMNLPClientBuilderForTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NLPResourceTest {

    @Test
    @DisplayName("it sends request to nlp service and retrieve result")
    void cenGetNLP() {
        NLPResource nlpResource = new NLPResource();
        List<EntitiesResult> entitiesResults = createEntitiesResults(0.213245);
        List<CategoriesResult> categoriesResults = createCategoriesResults(0.39);
        List<ConceptsResult> conceptsResults = createConceptsResults(0.917293);
        nlpResource.request = new Request(new IBMNLPClientBuilderForTest()
                .withEntitiesResult(entitiesResults)
                .withCategoriesResult(categoriesResults)
                .withConceptsResult(conceptsResults)
                .build());

        Response response = nlpResource.processRequest("{\"text\":\"Un texte à analyser\",\"minimumScore\":0.0}");

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(((Result)response.getEntity()).getEntitiesAnalysis().getEntities()).hasSize(2);
    }

    @Test
    @DisplayName("it retrieves result greater than or equal to expected score")
    void canGetResults() {
        NLPResource nlpResource = new NLPResource();
        List<EntitiesResult> entitiesResults = createEntitiesResults(0.213245);
        List<CategoriesResult> categoriesResults = createCategoriesResults(0.39);
        List<ConceptsResult> conceptsResults = createConceptsResults(0.9000000);
        nlpResource.request = new Request(new IBMNLPClientBuilderForTest()
                .withEntitiesResult(entitiesResults)
                .withCategoriesResult(categoriesResults)
                .withConceptsResult(conceptsResults)
                .build());

        Response response = nlpResource.processRequest("{\"text\":\"Un texte à analyser\",\"minimumScore\":0.90}");

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(((Result)response.getEntity()).getEntitiesAnalysis().getEntities()).hasSize(1);
        assertThat(((Result)response.getEntity()).getCategories()).hasSize(2);
        assertThat(((Result)response.getEntity()).getConcepts()).hasSize(3);
    }

    private ArrayList<ConceptsResult> createConceptsResults(final double conceptRelevance) {
        return Lists.newArrayList(
                new ConceptsResultBuilderForTest()
                        .withText("Chaussure")
                        .withRelevance(0.967468)
                        .withDbpediaResource("http://fr.dbpedia.org/resource/Chaussure")
                        .build(),
                new ConceptsResultBuilderForTest()
                        .withText("Cuir")
                        .withRelevance(0.964209)
                        .withDbpediaResource("http://fr.dbpedia.org/resource/Cuir")
                        .build(),
                new ConceptsResultBuilderForTest()
                        .withText("Pointures et tailles en habillement")
                        .withRelevance(conceptRelevance)
                        .withDbpediaResource("http://fr.dbpedia.org/resource/Pointures_et_tailles_en_habillement")
                        .build());
    }

    private ArrayList<CategoriesResult> createCategoriesResults(final double categoryScore) {
        return Lists.newArrayList(
                new CategoriesResultBuilderForTest()
                        .withLabel("/style and fashion/footwear/shoes")
                        .withScore(0.984345)
                        .build(),
                new CategoriesResultBuilderForTest()
                        .withLabel("/style and fashion/accessories/socks")
                        .withScore(0.9772656)
                        .build(),
                new CategoriesResultBuilderForTest().withLabel("/business and industrial/advertising and marketing/brand management")
                        .withScore(categoryScore)
                        .build());
    }

    private ArrayList<EntitiesResult> createEntitiesResults(final double entityRelevance) {
        return Lists.newArrayList(
                new EntitiesResultBuilderFortTest()
                        .withText("43")
                        .withType("Number")
                        .withRelevance(0.9646787)
                        .build(),
                new EntitiesResultBuilderFortTest()
                        .withText("minelli")
                        .withType("Person")
                        .withRelevance(entityRelevance)
                        .build());
    }
}
