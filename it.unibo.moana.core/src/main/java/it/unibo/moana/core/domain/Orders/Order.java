package it.unibo.moana.core.domain.Orders;

public class Order {
	protected String id;
	protected String description;
	protected Client client;
	
	public Order(String id, String description, Client client){
		this.id = id;
		this.description = description;
		this.client = client;
	}
}
