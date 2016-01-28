package it.unibo.moana.core.domain;

public abstract class Entity <TId>{
	private TId id;
	
	protected Entity(TId id) {
		this.id = id; 
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Entity))return false;
	    Entity<?> otherEntity = (Entity<?>)other;
	    
	    if(otherEntity.getId()== this.id)
	    	return true;
	    
	    return false;
	}
	
	public TId getId(){
		return id;
	}

}
