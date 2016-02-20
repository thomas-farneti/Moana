package it.unibo.persistence.mongo.mappings;

import org.mongolink.domain.mapper.ComponentMap;

import it.unibo.moana.core.domain.valueObjects.Dimension;

public class DimensionMapping extends ComponentMap<Dimension> {

	public DimensionMapping() {
		super();
	}
	
	@Override
	public void map() {
		property().onField("type");
		property().onField("value");
		property().onField("measure");	
	}

}
