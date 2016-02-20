package it.unibo.moana.core.domain.orders;

import it.unibo.moana.core.domain.IEntity;
import it.unibo.moana.core.domain.loadingUnloadingPoints.LoadingUnloadingPoint;
import it.unibo.moana.core.domain.valueObjects.Dimension;

public class Order implements IEntity<String>{
	
	protected String id;
	protected String description;
	protected Dimension demand;
	protected LoadingUnloadingPoint destination;
	
	protected Order(){}
	
	public Order(String id, String description, Dimension demand, LoadingUnloadingPoint client){
		this.id=id;
		this.description = description;
		this.destination = client;
		this.demand= demand;
	}
	
	
	public LoadingUnloadingPoint getDestination(){
		return this.destination;
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	public String getDestinationDescription()
	{
		return destination.getDescription();
	}
	
	public String getDestinationId()
	{
		return destination.getId();
	}
	
	public String getDescription() {
		return description;
	}


	public Dimension getDemand(){
		return this.demand;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void updateOrder(Order o){
		this.demand = o.getDemand();
		this.description = o.getDescription();
		this.destination = o.getDestination();
	}
}
