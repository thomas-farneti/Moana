package it.unibo.moana.core.domain;

public class Entity <K> {
	protected K id;
	
	public Entity(K id){
		this.id = id;
	}
	
	public K getId(){
		return id;
	}
}
