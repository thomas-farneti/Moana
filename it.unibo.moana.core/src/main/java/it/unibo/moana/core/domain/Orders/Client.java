package it.unibo.moana.core.domain.Orders;

import it.unibo.moana.core.domain.valueObjects.Position;

public class Client {
	protected String id;
	protected String description;
	protected Position position;
	
	public Client(String id, String description, Position position){
		this.id = id;
		this.description = description;
		this.position = position;
	}
}
