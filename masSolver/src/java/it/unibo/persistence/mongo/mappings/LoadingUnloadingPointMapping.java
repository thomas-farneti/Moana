package it.unibo.persistence.mongo.mappings;

import org.mongolink.domain.mapper.AggregateMap;

import it.unibo.moana.core.domain.loadingUnloadingPoints.LoadingUnloadingPoint;

public class LoadingUnloadingPointMapping extends AggregateMap<LoadingUnloadingPoint>{
	public LoadingUnloadingPointMapping() {
		super();
	}

	@Override
	public void map() {
		id().onProperty(element().getId()).natural();
		property().onProperty(element().getDescription());
		property().onProperty(element().getPosition());
	}

}
