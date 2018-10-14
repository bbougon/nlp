package fr.bbougon.nlp.ibm;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import fr.bbougon.nlp.domain.EntitiesAnalyze;
import fr.bbougon.nlp.domain.NLPEntity;

import java.util.List;
import java.util.stream.Collectors;

public class IBMEntitiesAnalyze implements EntitiesAnalyze {

    IBMEntitiesAnalyze(final AnalysisResults analysisResults) {
        this.analysisResults = analysisResults;
    }

    @Override
    public List<NLPEntity> getEntities() {
        return analysisResults.getEntities().stream()
                .map(IBMNLPEntity::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getLanguage() {
        return analysisResults.getLanguage();
    }

    private AnalysisResults analysisResults;
}
