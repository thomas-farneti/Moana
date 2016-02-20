package it.unibo.moana.core.domain.valueObjects;

public class Position {
	protected double latitude;
	protected double longitude;
	
	protected Position(){}
	
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
	
	public static Position empty(){
		return new Position(0, 0);
	}
}
