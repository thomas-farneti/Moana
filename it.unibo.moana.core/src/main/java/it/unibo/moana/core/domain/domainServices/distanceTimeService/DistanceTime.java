package it.unibo.moana.core.domain.domainServices.distanceTimeService;

public class DistanceTime {
	protected double kilometers;
	protected double minutes;
	
	public DistanceTime(double kilometers, double minutes) {
		this.kilometers = kilometers;
		this.minutes = minutes;
	}
	
	public double getKilometers() {
		return kilometers;
	}
	public void setKilometers(double kilometers) {
		this.kilometers = kilometers;
	}
	public double getMinutes() {
		return minutes;
	}
	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}
	
}
