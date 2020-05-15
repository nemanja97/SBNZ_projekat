package rs.ac.uns.ftn.sbnz.models.drools;

import rs.ac.uns.ftn.sbnz.models.Property;

import java.util.Comparator;

public class PropertyWithScore implements Comparable<PropertyWithScore>, Comparator<PropertyWithScore> {

    private Property property;
    private int score;

    public PropertyWithScore(Property property) {
        this.property = property;
        this.score = 0;
    }

    public PropertyWithScore(Property property, int score) {
        this.property = property;
        this.score = score;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compare(PropertyWithScore propertyWithScore1, PropertyWithScore propertyWithScore2) {
        return propertyWithScore1.getScore() - propertyWithScore2.getScore();
    }

    @Override
    public int compareTo(PropertyWithScore propertyWithScore) {
        return this.getScore() - propertyWithScore.getScore();
    }
}
