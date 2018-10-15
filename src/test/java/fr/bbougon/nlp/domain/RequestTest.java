package fr.bbougon.nlp.domain;

import com.google.common.collect.Lists;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;
import fr.bbougon.nlp.ibm.CategoriesResultBuilderForTest;
import fr.bbougon.nlp.ibm.ConceptsResultBuilderForTest;
import fr.bbougon.nlp.ibm.EntitiesResultBuilderFortTest;
import fr.bbougon.nlp.ibm.IBMNLPClientBuilderForTest;
import fr.bbougon.nlp.web.resources.TextToAnalyse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class RequestTest {

    @Test
    @DisplayName("send a request to IBM NLP engine")
    void ibm() {
        Result result = new Request(new IBMNLPClientBuilderForTest()
                .withEntitiesResult(createEntitiesResults())
                .withCategoriesResult(createCategoriesResults())
                .withConceptsResult(createConceptsResults())
                .build()).send(new TextToAnalyse("Paire de chaussure pointure 43 en cuir noir de marque minelli", BigDecimal.ZERO));

        assertThat(result.getEntitiesAnalysis()).isNotNull();
        assertThat(result.getEntitiesAnalysis().getEntities()).hasSize(2);
        assertEntity(result, 0, "43", "Number", new BigDecimal("0.96"));
        assertEntity(result, 1, "minelli", "Person", new BigDecimal("0.21"));
        assertThat(result.getEntitiesAnalysis().getLanguage()).isEqualTo("fr");
        assertThat(result.getCategories()).hasSize(3);
        assertThat(result.getCategories().get(0).getHierarchy()).isEqualTo("/style and fashion/footwear/shoes");
        assertThat(result.getCategories().get(0).getScore()).isEqualTo(new BigDecimal("0.98"));
        assertThat(result.getConcepts()).hasSize(3);
        assertConcept(result, 0, "Chaussure", new BigDecimal("0.97"));
        assertConcept(result, 1, "Cuir", new BigDecimal("0.96"));
        assertConcept(result, 2, "Pointures et tailles en habillement", new BigDecimal("0.92"));
    }

    private void assertConcept(final Result result, final int index, final String expectedConcept, final BigDecimal expectedScore) {
        assertThat(result.getConcepts().get(index).getName()).isEqualTo(expectedConcept);
        assertThat(result.getConcepts().get(index).getScore()).isEqualTo(expectedScore);
    }

    private void assertEntity(final Result result, final int index, final String expectedName, final String expectedType, final BigDecimal expectedRelevance) {
        NLPEntity entity = result.getEntitiesAnalysis().getEntities().get(index);
        assertThat(entity.getName()).isEqualTo(expectedName);
        assertThat(entity.getType()).isEqualTo(expectedType);
        assertThat(entity.getRelevance()).isEqualTo(expectedRelevance);
    }

    private ArrayList<ConceptsResult> createConceptsResults() {
        return Lists.newArrayList(new ConceptsResultBuilderForTest()
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
                        .withRelevance(0.917293)
                        .withDbpediaResource("http://fr.dbpedia.org/resource/Pointures_et_tailles_en_habillement")
                        .build());
    }

    private ArrayList<CategoriesResult> createCategoriesResults() {
        return Lists.newArrayList(new CategoriesResultBuilderForTest()
                        .withLabel("/style and fashion/footwear/shoes")
                        .withScore(0.984345)
                        .build(),
                new CategoriesResultBuilderForTest()
                        .withLabel("/style and fashion/accessories/socks")
                        .withScore(0.0772656)
                        .build(),
                new CategoriesResultBuilderForTest().withLabel("/business and industrial/advertising and marketing/brand management")
                        .withScore(0.39)
                        .build());
    }

    private ArrayList<EntitiesResult> createEntitiesResults() {
        return Lists.newArrayList(new EntitiesResultBuilderFortTest()
                        .withText("43")
                        .withType("Number")
                        .withRelevance(0.9646787)
                        .build(),
                new EntitiesResultBuilderFortTest()
                        .withText("minelli")
                        .withType("Person")
                        .withRelevance(0.213245)
                        .build());
    }
}