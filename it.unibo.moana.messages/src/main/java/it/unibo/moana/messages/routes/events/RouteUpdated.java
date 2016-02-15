package it.unibo.moana.messages.routes.events;

import java.util.Collection;

import it.unibo.moana.messages.IEvent;

public class RouteUpdated implements IEvent {
	public String routeId;
	public Collection<String> ordersServedIds;
	
	public RouteUpdated(String routeId, Collection<String> ordersIds) {
		this.routeId = routeId;
		this.ordersServedIds = ordersIds;
	}
}
