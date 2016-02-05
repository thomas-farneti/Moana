package it.unibo.moana.messages.orders.commands;

import it.unibo.moana.messages.ICommand;

public class UpdateOrderCommand implements ICommand{
	public String commandDescription;
	public String clientId;
	public double demand;
	public String clientDescription;
	public double longitude;
	public double latitude;

	public UpdateOrderCommand(
			String commandDescription,
			Double demand,
			String clientId, 
			String clientDescription, 
			double longitude,
			double latitude){
		this.commandDescription = commandDescription;
		this.demand = demand;
		this.clientId = clientId;
		this.clientDescription = clientDescription;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
