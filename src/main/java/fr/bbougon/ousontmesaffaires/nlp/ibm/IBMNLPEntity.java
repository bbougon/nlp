package fr.bbougon.ousontmesaffaires.nlp.ibm;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;
import fr.bbougon.ousontmesaffaires.nlp.domain.NLPEntity;

import java.math.BigDecimal;

public class IBMNLPEntity implements NLPEntity {
    public IBMNLPEntity(final EntitiesResult entity) {
        this.entity = entity;
    }

    @Override
    public String getName() {
        return entity.getText();
    }

    @Override
    public String getType() {
        return entity.getType();
    }

    @Override
    public BigDecimal getRelevance() {
        return new BigDecimal(Double.toString(entity.getRelevance()))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private final EntitiesResult entity;
}
