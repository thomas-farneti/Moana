package it.unibo.moana.core.domain.loadingUnloadingPoints;

import it.unibo.moana.core.domain.IEntity;
import it.unibo.moana.core.domain.valueObjects.Position;

public class LoadingUnloadingPoint implements IEntity<String> {
	protected String id;
	protected String description;
	protected Position position;
	
	public LoadingUnloadingPoint(String id, String description, Position position){
		this.id = id;
		this.description = description;
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	
	
}
