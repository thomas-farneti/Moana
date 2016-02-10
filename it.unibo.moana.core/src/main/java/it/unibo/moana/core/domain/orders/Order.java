package it.unibo.moana.core.domain.orders;

import it.unibo.moana.core.domain.IEntity;

public class Order implements IEntity<String>{
	
	protected String id;
	protected String description;
	protected Double demand;
	protected Client client;
	
	public Order(String id, String description, Client client){
		this.id=id;
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
	
	public Client getClient(){
		return this.client;
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	public double getOrderDemand(){
		return this.demand;
	}
}
