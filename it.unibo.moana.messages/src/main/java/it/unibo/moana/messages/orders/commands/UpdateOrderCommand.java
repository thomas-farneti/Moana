package it.unibo.moana.messages.orders.commands;

import it.unibo.moana.messages.ICommand;

public class UpdateOrderCommand implements ICommand{
	public String commandId;
	public String commandDescription;
	public String clientId;
	public String clientDescription;
	public double longitude;
	public double latitude;

	public UpdateOrderCommand(
			String commandId,
			String commandDescription,
			String clientId, 
			String clientDescription, 
			double longitude,
			double latitude){
		this.commandId = commandId;
		this.commandDescription = commandDescription;
		this.clientId = clientId;
		this.clientDescription = clientDescription;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
