package fr.bbougon.ousontmesaffaires.nlp.ibm;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import fr.bbougon.ousontmesaffaires.nlp.domain.Category;
import fr.bbougon.ousontmesaffaires.nlp.domain.Concept;
import fr.bbougon.ousontmesaffaires.nlp.domain.EntitiesAnalyze;
import fr.bbougon.ousontmesaffaires.nlp.domain.Result;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class IBMResult implements Result {

    IBMResult(final AnalysisResults analysisResults) {
        this.analysisResults = analysisResults;
    }

    @Override
    public EntitiesAnalyze getEntitiesAnalysis() {
        return new IBMEntitiesAnalyze(analysisResults);
    }

    @Override
    public List<Category> getCategories() {
        return analysisResults.getCategories().stream()
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
}

