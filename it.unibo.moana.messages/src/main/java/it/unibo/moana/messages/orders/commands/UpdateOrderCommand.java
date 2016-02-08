package it.unibo.moana.messages.orders.commands;

import it.unibo.moana.messages.ICommand;

public class UpdateOrderCommand implements ICommand{
	public String id;
	public String description;
	public String clientId;
	public double demand;
	public double positionLatitude;
	public double positionLongitude;

	public UpdateOrderCommand(
			String id,
			String description,
			Double demand,
			String clientId,
			double longitude,
			double latitude){
		this.id = id;
		this.description = description;
		this.demand = demand;
		this.clientId = clientId;
		this.positionLatitude = latitude;
		this.positionLongitude = longitude;
	}
}
