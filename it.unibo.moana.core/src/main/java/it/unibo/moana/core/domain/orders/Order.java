package it.unibo.moana.core.domain.orders;

import it.unibo.moana.core.domain.IEntity;
import it.unibo.moana.core.domain.loadingUnloadingPoints.LoadingUnloadingPoint;

public class Order implements IEntity<String>{
	
	protected String id;
	protected String description;
	protected double demand;
	protected LoadingUnloadingPoint destination;
	
	public Order(String id, String description, double demand, LoadingUnloadingPoint client){
		this.id=id;
		this.description = description;
		this.destination = client;
		this.demand= demand;
	}
	
	public String getDestinationDescription()
	{
		return destination.getDescription();
	}
	
	public String getDestinationId()
	{
		return destination.getId();
	}
	
	public LoadingUnloadingPoint getDestination(){
		return this.destination;
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	public double getOrderDemand(){
		return this.demand;
	}
}
