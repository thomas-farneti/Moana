package it.unibo.masSolver.output;

import java.util.Collection;
import java.util.HashMap;
import com.google.common.eventbus.Subscribe;

import it.unibo.moana.core.domainEvents.IHandler;
import it.unibo.moana.messages.routes.events.RouteUpdated;

public class UpdateOrderHandler implements IHandler {

	private HashMap<String,Collection<String>> routes;
	
	public UpdateOrderHandler() {
		routes = new HashMap<String,Collection<String>>();	
	}
	
	@Subscribe
	public void handle(RouteUpdated event) {
		Collection<String> currentValues = routes.get(event.routeId);
		if (currentValues != null)
			currentValues.addAll(event.ordersServedIds);
		else 
			currentValues = event.ordersServedIds;
		routes.put(event.routeId, currentValues);
	}
	
	public HashMap<String,Collection<String>> getRoutes(){
		return routes;
	}
}
