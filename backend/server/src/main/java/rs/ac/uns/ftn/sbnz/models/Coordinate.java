package rs.ac.uns.ftn.sbnz.models;

import javax.persistence.Embeddable;

@Embeddable
public class Coordinate {

    private Double latitude;
    private Double longitude;

    public Coordinate() {
    }

    public Coordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double calculateDistance(Coordinate other) {
		double theta = this.longitude - other.longitude;
		double dist = Math.sin(Math.toRadians(this.latitude)) * Math.sin(Math.toRadians(other.latitude)) +
				Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(other.latitude)) * Math.cos(Math.toRadians(theta));
		dist = Math.acos(dist);
		dist = Math.toDegrees(dist);
		dist *= 60 * 1.1515 * 1.609344;
		return dist;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
    
    
}
