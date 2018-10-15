package fr.bbougon.nlp.ibm;

import com.google.inject.Inject;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import fr.bbougon.nlp.domain.NLPClient;
import fr.bbougon.nlp.domain.NLPSettings;
import fr.bbougon.nlp.domain.Result;
import fr.bbougon.nlp.web.resources.TextToAnalyse;

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
    public Result processRequest(final TextToAnalyse textToAnalyse) {
        return new IBMResult(naturalLanguageUnderstanding.analyze(buildParameters(textToAnalyse.text)).execute(), textToAnalyse.minimumScore);
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
