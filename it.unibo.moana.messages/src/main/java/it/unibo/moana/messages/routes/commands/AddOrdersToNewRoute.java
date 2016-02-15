package it.unibo.moana.messages.routes.commands;

import java.util.Collection;

import it.unibo.moana.messages.ICommand;

public class AddOrdersToNewRoute implements ICommand {
	public String routeId;
	public Collection<String> ordersIds;
	public String depotId;
	
	public AddOrdersToNewRoute(String routeId,Collection<String> ordersIds, String depotId) {
		this.routeId= routeId;
		this.ordersIds = ordersIds;
		this.depotId = depotId;
	}	
}
