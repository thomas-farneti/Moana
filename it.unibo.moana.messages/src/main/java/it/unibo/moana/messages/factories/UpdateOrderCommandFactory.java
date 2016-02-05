package it.unibo.moana.messages.factories;

import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;

public class UpdateOrderCommandFactory {
	public static UpdateOrderCommand getInstance(
			String commandDescription,
			Double demand,
			String clientId,
			String clientDescription,
			double longitude,
			double latitude){
		return new UpdateOrderCommand(
				commandDescription, 
				demand,
				clientId, 
				clientDescription, 
				latitude,
				longitude);
	}
}
