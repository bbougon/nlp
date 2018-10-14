package fr.bbougon.nlp.web.providers.mappers.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.bbougon.nlp.domain.Concept;

import java.util.List;

public class ConceptsMapper extends JsonMapper<JsonArray, List<Concept>> {

    @Override
    public JsonArray map(final List<Concept> objectToMap) {
        JsonArray concepts = new JsonArray();
        objectToMap.forEach(concept -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", concept.getName());
            jsonObject.addProperty("score", concept.getScore().doubleValue());
            concepts.add(jsonObject);
        });
        return concepts;
    }
}
