package it.unibo.moana.messages.routes.commands;

import it.unibo.moana.messages.ICommand;

public class AddNewRoute implements ICommand{
	public String routeId;
	public String depotId;
	public AddNewRoute(String routeId, String depotId) {
		this.routeId = routeId;
		this.depotId = depotId;
	}
}
