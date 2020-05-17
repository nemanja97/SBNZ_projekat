package rs.ac.uns.ftn.sbnz.models.drools;

import java.util.ArrayList;
import java.util.List;

public class PropertyScaler {

    // PROPERTY SCALES
    private double priceScale;
    private double sizeScale;
    private double bedroomScale;
    private double bathroomScale;
    private double heatingScale;
    private double petScale;
    private double elevatorScale;
    private double airConditioningScale;
    private double cableReadyScale;
    private double highSpeedInternetScale;
    private double swimmingPoolScale;
    private double garageScale;
    private double securityScale;
    private double gatedScale;

    // DISTANCE FACTOR
    private double nearbyDistanceFactor;

    // PLACE OF INTEREST SCALES
    private double kindergartenScale;
    private double schoolScale;
    private double universityScale;
    private double hospitalScale;
    private double bankScale;
    private double parkScale;
    private double gymScale;
    private double restaurantScale;
    private double theaterScale;
    private double cinemaScale;
    private double supermarketScale;
    private double shoppingCenterScale;
    private double nightClubScale;

    private List<String> firedRules;

    public PropertyScaler() {
        // PROPERTY SCALES
        this.priceScale = 1.0;
        this.sizeScale = 1.0;
        this.bedroomScale = 1.0;
        this.bathroomScale = 1.0;
        this.heatingScale = 1.0;
        this.petScale = 1.0;
        this.elevatorScale = 1.0;
        this.airConditioningScale = 1.0;
        this.cableReadyScale = 1.0;
        this.highSpeedInternetScale = 1.0;
        this.swimmingPoolScale = 1.0;
        this.garageScale = 1.0;
        this.securityScale = 1.0;
        this.gatedScale = 1.0;
        
        // DISTANCE FACTOR
        this.nearbyDistanceFactor = 5.0;
        
        // PLACE OF INTEREST SCALES
        this.kindergartenScale = 1.0;
        this.schoolScale = 1.0;
        this.universityScale = 1.0;
        this.hospitalScale = 1.0;
        this.bankScale = 1.0;
        this.parkScale = 1.0;
        this.gymScale = 1.0;
        this.restaurantScale = 1.0;
        this.theaterScale = 1.0;
        this.cinemaScale = 1.0;
        this.supermarketScale = 1.0;
        this.shoppingCenterScale = 1.0;
        this.nightClubScale = 1.0;
        this.firedRules = new ArrayList<>();
    }

    public double getPriceScale() {
        return priceScale;
    }

    public void setPriceScale(double priceScale) {
        this.priceScale = priceScale;
    }

    public double getSizeScale() {
        return sizeScale;
    }

    public void setSizeScale(double sizeScale) {
        this.sizeScale = sizeScale;
    }

    public double getBedroomScale() {
        return bedroomScale;
    }

    public void setBedroomScale(double bedroomScale) {
        this.bedroomScale = bedroomScale;
    }

    public double getBathroomScale() {
        return bathroomScale;
    }

    public void setBathroomScale(double bathroomScale) {
        this.bathroomScale = bathroomScale;
    }

    public double getHeatingScale() {
        return heatingScale;
    }

    public void setHeatingScale(double heatingScale) {
        this.heatingScale = heatingScale;
    }

    public double getPetScale() {
        return petScale;
    }

    public void setPetScale(double petScale) {
        this.petScale = petScale;
    }

    public double getElevatorScale() {
        return elevatorScale;
    }

    public void setElevatorScale(double elevatorScale) {
        this.elevatorScale = elevatorScale;
    }

    public double getAirConditioningScale() {
        return airConditioningScale;
    }

    public void setAirConditioningScale(double airConditioningScale) {
        this.airConditioningScale = airConditioningScale;
    }

    public double getCableReadyScale() {
        return cableReadyScale;
    }

    public void setCableReadyScale(double cableReadyScale) {
        this.cableReadyScale = cableReadyScale;
    }

    public double getHighSpeedInternetScale() {
        return highSpeedInternetScale;
    }

    public void setHighSpeedInternetScale(double highSpeedInternetScale) {
        this.highSpeedInternetScale = highSpeedInternetScale;
    }

    public double getSwimmingPoolScale() {
        return swimmingPoolScale;
    }

    public void setSwimmingPoolScale(double swimmingPoolScale) {
        this.swimmingPoolScale = swimmingPoolScale;
    }

    public double getGarageScale() {
        return garageScale;
    }

    public void setGarageScale(double garageScale) {
        this.garageScale = garageScale;
    }

    public double getSecurityScale() {
        return securityScale;
    }

    public void setSecurityScale(double securityScale) {
        this.securityScale = securityScale;
    }

    public double getGatedScale() {
        return gatedScale;
    }

    public void setGatedScale(double gatedScale) {
        this.gatedScale = gatedScale;
    }

    public double getNearbyDistanceFactor() {
        return nearbyDistanceFactor;
    }

    public void setNearbyDistanceFactor(double nearbyDistanceFactor) {
        this.nearbyDistanceFactor = nearbyDistanceFactor;
    }

    public double getKindergartenScale() {
        return kindergartenScale;
    }

    public void setKindergartenScale(double kindergartenScale) {
        this.kindergartenScale = kindergartenScale;
    }

    public double getSchoolScale() {
        return schoolScale;
    }

    public void setSchoolScale(double schoolScale) {
        this.schoolScale = schoolScale;
    }

    public double getUniversityScale() {
        return universityScale;
    }

    public void setUniversityScale(double universityScale) {
        this.universityScale = universityScale;
    }

    public double getHospitalScale() {
        return hospitalScale;
    }

    public void setHospitalScale(double hospitalScale) {
        this.hospitalScale = hospitalScale;
    }

    public double getBankScale() {
        return bankScale;
    }

    public void setBankScale(double bankScale) {
        this.bankScale = bankScale;
    }

    public double getParkScale() {
        return parkScale;
    }

    public void setParkScale(double parkScale) {
        this.parkScale = parkScale;
    }

    public double getGymScale() {
        return gymScale;
    }

    public void setGymScale(double gymScale) {
        this.gymScale = gymScale;
    }

    public double getRestaurantScale() {
        return restaurantScale;
    }

    public void setRestaurantScale(double restaurantScale) {
        this.restaurantScale = restaurantScale;
    }

    public double getTheaterScale() {
        return theaterScale;
    }

    public void setTheaterScale(double theaterScale) {
        this.theaterScale = theaterScale;
    }

    public double getCinemaScale() {
        return cinemaScale;
    }

    public void setCinemaScale(double cinemaScale) {
        this.cinemaScale = cinemaScale;
    }

    public double getSupermarketScale() {
        return supermarketScale;
    }

    public void setSupermarketScale(double supermarketScale) {
        this.supermarketScale = supermarketScale;
    }

    public double getShoppingCenterScale() {
        return shoppingCenterScale;
    }

    public void setShoppingCenterScale(double shoppingCenterScale) {
        this.shoppingCenterScale = shoppingCenterScale;
    }

    public double getNightClubScale() {
        return nightClubScale;
    }

    public void setNightClubScale(double nightClubScale) {
        this.nightClubScale = nightClubScale;
    }

    public List<String> getFiredRules() {
        return firedRules;
    }

    public void setFiredRules(List<String> firedRules) {
        this.firedRules = firedRules;
    }
}
