package it.unibo.moana.messages.orders.events;

import it.unibo.moana.messages.IEvent;

public class OrderUpdatedEvent implements IEvent {
	public String orderId;
	public double demand;
	public String clientId;
	
	public OrderUpdatedEvent(String orderId, double demand, String clientId) {
		super();
		this.orderId = orderId;
		this.demand = demand;
		this.clientId = clientId;
	}
}
