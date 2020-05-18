package rs.ac.uns.ftn.sbnz.models.drools;

import rs.ac.uns.ftn.sbnz.models.Property;

import java.util.Comparator;

public class PropertyWithScore implements Comparable<PropertyWithScore>, Comparator<PropertyWithScore> {

    private Property property;
    private PropertyScaler scaler;
    private double score;

    public PropertyWithScore(Property property) {
        this.property = property;
        this.scaler = new PropertyScaler();
        this.score = 0;
    }

    public PropertyWithScore(Property property, PropertyScaler calculator, double score) {
        this.property = property;
        this.scaler = calculator;
        this.score = score;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public PropertyScaler getScaler() {
        return scaler;
    }

    public void setScaler(PropertyScaler scaler) {
        this.scaler = scaler;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public int compare(PropertyWithScore propertyWithScore1, PropertyWithScore propertyWithScore2) {
        return (int) -(propertyWithScore1.getScore() - propertyWithScore2.getScore());
    }

    @Override
    public int compareTo(PropertyWithScore propertyWithScore) {
        return (int) -(this.getScore() - propertyWithScore.getScore());
    }
}
