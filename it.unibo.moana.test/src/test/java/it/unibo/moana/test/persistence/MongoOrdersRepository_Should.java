package it.unibo.moana.test.persistence;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.UUID;

import org.junit.Test;
import org.mongolink.MongoSession;

import it.unibo.moana.core.domain.loadingUnloadingPoints.LoadingUnloadingPoint;
import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.core.domain.valueObjects.Dimension;
import it.unibo.moana.core.domain.valueObjects.Position;
import it.unibo.moana.infrastructure.Configurator;
import it.unibo.moana.infrastructure.MoanaSettings;

public class MongoOrdersRepository_Should {

	@Test
	public void testAddOrder() throws UnknownHostException {
		Configurator config = Configurator.GetInstance(new MoanaSettings().getDefault());
		
		String id = UUID.randomUUID().toString();
		
		Dimension dim = new Dimension("Test", 100,"#") ;
		LoadingUnloadingPoint destination = new LoadingUnloadingPoint(UUID.randomUUID().toString(), "testClient", new Position(0, 0));
		Order order = new Order(id, "test", dim, destination);
		
		config.getOrdersRepo().addOrUpdate(order);
		
		Order o = config.getOrdersRepo().load(id);
		
		assertTrue(o.getId().equals(order.getId()));
		assertTrue(dim.getValue() == o.getDemand().getValue());
		assertTrue(destination.getId().equals(o.getDestinationId()));
		
		config.getOrdersRepo().remove(o);
	}
	
	@Test
	public void testUpdateOrder() throws UnknownHostException {
		Configurator config = Configurator.GetInstance(new MoanaSettings().getDefault());
		
		String id = UUID.randomUUID().toString();
		
		Dimension dim = new Dimension("Test", 100,"#") ;
		LoadingUnloadingPoint destination = new LoadingUnloadingPoint(UUID.randomUUID().toString(), "testClient", new Position(0, 0));
		Order order = new Order(id, "test", dim, destination);
		
		config.getOrdersRepo().addOrUpdate(order);
		
		Order o = config.getOrdersRepo().load(id);
		
		assertTrue(o.getId().equals(order.getId()));
		assertTrue(dim.getValue() == o.getDemand().getValue());
		assertTrue(destination.getId().equals(o.getDestinationId()));
		
		o.setDescription("ordineModificato");
		
		config.getOrdersRepo().addOrUpdate(o);
		
		o = config.getOrdersRepo().load(id);
		
		assertTrue(o.getDescription().equals("ordineModificato"));
	}

}
