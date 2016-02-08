package it.unibo.moana.messages.orders.events;

import it.unibo.moana.messages.IEvent;

public class OrderUpdatedEvent implements IEvent {
	protected String id;
	
	public OrderUpdatedEvent(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
}
