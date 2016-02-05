package it.unibo.moana.core.domain.Orders;

import it.unibo.moana.core.domain.Entity;

public class Order extends Entity<String>{
	public String description;
	protected Client client;
	
	public Order(String id, String description, Client client){
		super(id);
		this.description = description;
		this.client = client;
	}
	
	public String getClientDescription()
	{
		return client.description;
	}
	
	public String getClientId()
	{
		return client.id;
	}
	
	public Double getLatitude()
	{
		return client.position.getLatitude();
	}
	
	public Double getLongitude()
	{
		return client.position.getLongitude();
	}
}
