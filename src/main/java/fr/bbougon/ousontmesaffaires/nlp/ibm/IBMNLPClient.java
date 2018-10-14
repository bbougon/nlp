package fr.bbougon.ousontmesaffaires.nlp.ibm;

import com.google.inject.Inject;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import fr.bbougon.ousontmesaffaires.nlp.domain.NLPClient;
import fr.bbougon.ousontmesaffaires.nlp.domain.NLPSettings;
import fr.bbougon.ousontmesaffaires.nlp.domain.Result;

public class IBMNLPClient implements NLPClient {

    @Inject
    public IBMNLPClient(NLPSettings settings) {
        naturalLanguageUnderstanding = new NaturalLanguageUnderstanding(settings.getVersion());
        naturalLanguageUnderstanding.setUsernameAndPassword(settings.getUsername(), settings.getPassword());
    }

    IBMNLPClient(final NaturalLanguageUnderstanding naturalLanguageUnderstanding) {
        this.naturalLanguageUnderstanding = naturalLanguageUnderstanding;
    }

    @Override
    public Result processRequest(final String text) {
        return new IBMResult(naturalLanguageUnderstanding.analyze(buildParameters(text)).execute());
    }

    private AnalyzeOptions buildParameters(final String text) {
        return new AnalyzeOptions.Builder()
                .text(text)
                .features(buildFeatures())
                .build();
    }

    private Features buildFeatures() {
        return new Features.Builder()
                .entities(new EntitiesOptions.Builder()
                        .build())
                .categories(new CategoriesOptions())
                .concepts(new ConceptsOptions.Builder().build())
                .build();
    }

    private final NaturalLanguageUnderstanding naturalLanguageUnderstanding;
}
