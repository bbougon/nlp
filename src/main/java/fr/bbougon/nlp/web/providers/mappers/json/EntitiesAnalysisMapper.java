package fr.bbougon.nlp.web.providers.mappers.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.bbougon.nlp.domain.EntitiesAnalyze;

public class EntitiesAnalysisMapper extends JsonMapper<JsonObject, EntitiesAnalyze> {

    @Override
    public JsonObject map(final EntitiesAnalyze objectToMap) {
        JsonObject entitiesAnalysis = new JsonObject();
        JsonArray jsonElements = new JsonArray();
        objectToMap.getEntities().forEach(nlpEntity -> {
            JsonObject element = new JsonObject();
            element.addProperty("name", nlpEntity.getName());
            element.addProperty("type", nlpEntity.getType());
            element.addProperty("relevance", nlpEntity.getRelevance().doubleValue());
            jsonElements.add(element);
        });
        entitiesAnalysis.add("entities", jsonElements);
        entitiesAnalysis.addProperty("language", objectToMap.getLanguage());
        return entitiesAnalysis;
    }
}
