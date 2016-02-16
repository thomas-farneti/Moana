package it.unibo.moana.messages.routes.commands;

import java.util.Collection;

import it.unibo.moana.messages.ICommand;

public class AddOrdersToRoute implements ICommand {
	public String routeId;
	public Collection<String> ordersIds;
	
	public AddOrdersToRoute(String routeId,Collection<String> ordersIds) {
		this.routeId= routeId;
		this.ordersIds = ordersIds;
	}
}
