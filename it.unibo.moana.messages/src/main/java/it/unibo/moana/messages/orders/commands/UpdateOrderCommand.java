package it.unibo.moana.messages.orders.commands;


import it.unibo.moana.messages.ICommand;

public class UpdateOrderCommand implements ICommand{
	public String id;
	public String description;
	public String clientId;
	public double dimensionValue;
	public String dimensionType;
	public String dimensionMeasure;
	public double positionLatitude;
	public double positionLongitude;
	
	public UpdateOrderCommand(String id, String description, String clientId, double dimensionValue,
			String dimensionType, String dimensionMeasure, double positionLatitude,
			double positionLongitude) {
		this.id = id;
		this.description = description;
		this.clientId = clientId;
		this.dimensionValue = dimensionValue;
		this.dimensionType = dimensionType;
		this.dimensionMeasure = dimensionMeasure;
		this.positionLatitude = positionLatitude;
		this.positionLongitude = positionLongitude;
	}

	
}
