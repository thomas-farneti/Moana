package it.unibo.moana.messages.orders.commands;

import it.unibo.moana.messages.ICommand;

public class UpdateOrderCommand implements ICommand{
	public String id;
	public String description;
	public Client client;
	
	public UpdateOrderCommand(String id, String description, Client client){
		this.id = id;
		this.description = description;
		this.client = client;
	}
	
	public class Client {
		public String id;
		public String description;
		public Position position;
		
		public Client(String id, String description, Position position){
			this.id = id;
			this.description = description;
			this.position = position;
		}
		
		public class Position {
			public double latitude;
			public double longitude;
			
			public Position(double latitude,double longitude){
				this.latitude = latitude;
				this.longitude = longitude;
			}
		}
	}
}
