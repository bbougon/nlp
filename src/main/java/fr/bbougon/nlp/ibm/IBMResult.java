package fr.bbougon.nlp.ibm;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import fr.bbougon.nlp.domain.Category;
import fr.bbougon.nlp.domain.Concept;
import fr.bbougon.nlp.domain.EntitiesAnalyze;
import fr.bbougon.nlp.domain.NLPEntity;
import fr.bbougon.nlp.domain.Result;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class IBMResult implements Result {

    IBMResult(final AnalysisResults analysisResults, final Double minimumScore) {
        this.analysisResults = analysisResults;
        this.minimumScore = minimumScore;
    }

    @Override
    public EntitiesAnalyze getEntitiesAnalysis() {
        return new EntitiesAnalyze() {
            @Override
            public List<NLPEntity> getEntities() {
                return analysisResults.getEntities().stream()
                        .filter(entitiesResult -> entitiesResult.getRelevance() >= minimumScore)
                        .map(IBMNLPEntity::new)
                        .collect(Collectors.toList());
            }

            @Override
            public String getLanguage() {
                return analysisResults.getLanguage();
            }

        };
    }

    @Override
    public List<Category> getCategories() {
        return analysisResults.getCategories().stream()
                .filter(categoriesResult -> categoriesResult.getScore() >= minimumScore)
                .map(analysisResult -> new Category() {
                    @Override
                    public String getHierarchy() {
                        return analysisResult.getLabel();
                    }

                    @Override
                    public BigDecimal getScore() {
                        return new BigDecimal(Double.toString(analysisResult.getScore())).setScale(2, RoundingMode.HALF_UP);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Concept> getConcepts() {
        return analysisResults.getConcepts().stream()
                .filter(conceptsResult -> conceptsResult.getRelevance() >= minimumScore)
                .map(conceptsResult -> new Concept() {
                    @Override
                    public String getName() {
                        return conceptsResult.getText();
                    }

                    @Override
                    public BigDecimal getScore() {
                        return new BigDecimal(Double.toString(conceptsResult.getRelevance())).setScale(2, RoundingMode.HALF_UP);
                    }
                })
                .collect(Collectors.toList());
    }

    private final AnalysisResults analysisResults;
    private Double minimumScore;
}

