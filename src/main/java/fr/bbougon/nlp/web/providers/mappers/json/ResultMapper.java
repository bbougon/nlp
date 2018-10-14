package fr.bbougon.nlp.web.providers.mappers.json;

import com.google.gson.JsonObject;
import fr.bbougon.nlp.domain.Result;

public class ResultMapper extends JsonMapper<JsonObject, Result> {

    @Override
    public JsonObject map(final Result objectToMap) {
        JsonObject jsonResult = new JsonObject();
        jsonResult.add("entitiesAnalysis", new EntitiesAnalysisMapper().map(objectToMap.getEntitiesAnalysis()));
        jsonResult.add("categories", new CategoriesMapper().map(objectToMap.getCategories()));
        jsonResult.add("concepts", new ConceptsMapper().map(objectToMap.getConcepts()));
        return jsonResult;
    }
}
