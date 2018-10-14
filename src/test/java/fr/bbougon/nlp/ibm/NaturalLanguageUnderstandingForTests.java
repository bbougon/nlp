package fr.bbougon.nlp.ibm;

import com.google.common.collect.Lists;
import com.ibm.watson.developer_cloud.http.Response;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.http.ServiceCallback;
import com.ibm.watson.developer_cloud.http.ServiceCallbackWithDetails;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;
import jersey.repackaged.jsr166e.CompletableFuture;

import java.util.List;

public class NaturalLanguageUnderstandingForTests extends NaturalLanguageUnderstanding {

    NaturalLanguageUnderstandingForTests() {
        super("2018-10-01");
    }

    @Override
    public ServiceCall<AnalysisResults> analyze(final AnalyzeOptions analyzeOptions) {
        return new ServiceCall<AnalysisResults>() {
            @Override
            public ServiceCall<AnalysisResults> addHeader(final String s, final String s1) {
                return null;
            }

            @Override
            public AnalysisResults execute() throws RuntimeException {
                return new AnalysisResults() {
                    @Override
                    public List<EntitiesResult> getEntities() {
                        if (analyzeOptions.features().entities() == null) {
                            return Lists.newArrayList();
                        }
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

                    @Override
                    public String getLanguage() {
                        return "fr";
                    }

                    @Override
                    public List<CategoriesResult> getCategories() {
                        if (analyzeOptions.features().categories() == null) {
                            return Lists.newArrayList();
                        }
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

                    @Override
                    public List<ConceptsResult> getConcepts() {
                        if (analyzeOptions.features().concepts() == null) {
                            return Lists.newArrayList();
                        }
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
                };
            }

            @Override
            public Response<AnalysisResults> executeWithDetails() throws RuntimeException {
                return null;
            }

            @Override
            public void enqueue(final ServiceCallback<? super AnalysisResults> serviceCallback) {

            }

            @Override
            public void enqueueWithDetails(final ServiceCallbackWithDetails<AnalysisResults> serviceCallbackWithDetails) {

            }

            @Override
            public CompletableFuture<AnalysisResults> rx() {
                return null;
            }

            @Override
            public CompletableFuture<Response<AnalysisResults>> rxWithDetails() {
                return null;
            }
        };
    }
}
