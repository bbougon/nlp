package fr.bbougon.nlp.domain;

import com.google.common.collect.Lists;

import java.util.List;

public class EntitiesAnalyzeBuilderForTest {
    public EntitiesAnalyze build() {
        return new EntitiesAnalyze() {
            @Override
            public List<NLPEntity> getEntities() {
                return Lists.newArrayList(new NLPEntityBuilderForTest().build());
            }

            @Override
            public String getLanguage() {
                return "fr";
            }
        };
    }
}
