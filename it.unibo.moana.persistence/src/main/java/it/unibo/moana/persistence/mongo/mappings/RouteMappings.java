package it.unibo.moana.persistence.mongo.mappings;

import org.mongolink.domain.mapper.AggregateMap;

import it.unibo.moana.core.domain.routes.Route;

public class RouteMappings extends AggregateMap<Route> {

	@Override
	public void map() {
		id().onProperty(element().getId()).natural();
		collection().onProperty(element().getOrders());
		property().onProperty(element().getDepot());
		property().onProperty(element().getCurrentLoad());
	}

}
