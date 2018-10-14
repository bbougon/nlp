package fr.bbougon.nlp.web.providers.mappers.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.bbougon.nlp.domain.Category;

import java.util.List;

public class CategoriesMapper extends JsonMapper<JsonArray, List<Category>> {

    @Override
    public JsonArray map(final List<Category> objectToMap) {
        JsonArray categories = new JsonArray();
        objectToMap.forEach(category -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("hierarchy", category.getHierarchy());
            jsonObject.addProperty("score", category.getScore().doubleValue());
            categories.add(jsonObject);
        });
        return categories;
    }
}
