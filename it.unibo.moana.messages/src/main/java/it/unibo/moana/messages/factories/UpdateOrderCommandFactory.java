package it.unibo.moana.messages.factories;

import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;

public class UpdateOrderCommandFactory {
	public static UpdateOrderCommand getInstance(
			String commandId,
			String commandDescription,
			String clientId,
			String clientDescription,
			double longitude,
			double latitude){
		return new UpdateOrderCommand(
				commandId,
				commandDescription, 
				clientId, 
				clientDescription, 
				latitude,
				longitude);
	}
}
