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

    NaturalLanguageUnderstandingForTests(final List<EntitiesResult> entitiesResults, final List<CategoriesResult> categoriesResults, final List<ConceptsResult> conceptsResults) {
        super("2018-09-21");
        this.entitiesResults = entitiesResults;
        this.categoriesResults = categoriesResults;
        this.conceptsResults = conceptsResults;
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
                        return entitiesResults;
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
                        return categoriesResults;
                    }

                    @Override
                    public List<ConceptsResult> getConcepts() {
                        if (analyzeOptions.features().concepts() == null) {
                            return Lists.newArrayList();
                        }
                        return conceptsResults;
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

    private List<EntitiesResult> entitiesResults;
    private List<CategoriesResult> categoriesResults;
    private List<ConceptsResult> conceptsResults;
}
