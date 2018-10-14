package fr.bbougon.nlp.web.providers.mappers.json;

public abstract class JsonMapper<T, V> {

    public abstract T map(V objectToMap);

}
