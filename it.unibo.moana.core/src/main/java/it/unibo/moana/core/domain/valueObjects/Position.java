package it.unibo.moana.core.domain.valueObjects;

public class Position {
	protected double latitude;
	protected double longitude;
	
	public Position(double latitude,double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}
}
