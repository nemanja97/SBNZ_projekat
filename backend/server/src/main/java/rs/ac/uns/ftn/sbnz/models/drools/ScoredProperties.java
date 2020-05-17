package rs.ac.uns.ftn.sbnz.models.drools;

import java.util.List;

public class ScoredProperties {

    private List<PropertyWithScore> propertyWithScores;

    public ScoredProperties(List<PropertyWithScore> propertyWithScores) {
        this.propertyWithScores = propertyWithScores;
    }

    public List<PropertyWithScore> getPropertyWithScores() {
        return propertyWithScores;
    }

    public void setPropertyWithScores(List<PropertyWithScore> propertyWithScores) {
        this.propertyWithScores = propertyWithScores;
    }
}
