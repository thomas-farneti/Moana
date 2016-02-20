package it.unibo.moana.persistence.mongo.mappings;

import org.mongolink.domain.mapper.ComponentMap;

import it.unibo.moana.core.domain.valueObjects.Position;

public class PositionMapping extends ComponentMap<Position>{

	public PositionMapping() {
		super();
	}
	
	@Override
	public void map() {
		property().onProperty(element().getLatitude());
		property().onProperty(element().getLongitude());
		
	}

}
