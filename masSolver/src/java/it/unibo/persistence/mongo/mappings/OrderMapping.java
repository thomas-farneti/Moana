package it.unibo.persistence.mongo.mappings;

import org.mongolink.domain.mapper.AggregateMap;

import it.unibo.moana.core.domain.orders.Order;

public class OrderMapping extends AggregateMap<Order>{
	public OrderMapping () {
	        super();
	    }
	@Override
	public void map() {
        id().onProperty(element().getId()).natural();
        property().onProperty(element().getDemand());
        property().onProperty(element().getDestination());
        property().onProperty(element().getDescription());
	}
}
