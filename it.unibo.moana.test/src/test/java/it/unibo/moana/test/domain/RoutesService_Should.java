package it.unibo.moana.test.domain;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Test;

import it.unibo.moana.core.domain.loadingUnloadingPoints.LoadingUnloadingPoint;
import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.core.domain.valueObjects.Dimension;
import it.unibo.moana.infrastructure.Configurator;
import it.unibo.moana.infrastructure.MoanaSettings;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import it.unibo.moana.messages.routes.commands.AddNewRoute;
import it.unibo.moana.messages.routes.commands.AddOrdersToNewRoute;
import it.unibo.moana.messages.routes.commands.AddOrdersToRoute;
import it.unibo.moana.messages.routes.query.QueryRoutesDetailsById;
import it.unibo.moana.messages.routes.query.QueryRoutesDetailsByIdResult;

public class RoutesService_Should {

	@Test
	public void addNewRoute() throws Exception {
		Configurator config = Configurator.GetInstance(new MoanaSettings().getTestDefault());
		
		String id = UUID.randomUUID().toString();
		
		config.getBus().Send(new AddNewRoute(id, "depot"));
		
		QueryRoutesDetailsById query = new QueryRoutesDetailsById(Arrays.asList(new String[]{id}));
		
		assertTrue(id == config.getRoutesReadModel().query(query).results.iterator().next().routeId);
	}
	
	@Test
	public void addOrdersToNewRoute() throws Exception {
		Configurator config = Configurator.GetInstance(new MoanaSettings().getDefault());
		
		String id = UUID.randomUUID().toString();
		
		ArrayList<String> orders = new ArrayList<>();
		
		for(int cont =0 ; cont<5 ; cont++){
			orders.add("order_"+cont);
			config.getBus().Send(new UpdateOrderCommand("order_"+cont, "order_"+cont, "testClient", 10, "", "", 10.0, 10.0));
		}
				
		config.getBus().Send(new AddOrdersToNewRoute(id, orders , "depot"));
		
		QueryRoutesDetailsById query = new QueryRoutesDetailsById(Arrays.asList(new String[]{id}));
		
		assertTrue(id.equals(config.getRoutesReadModel().query(query).results.iterator().next().routeId));
	}
	
	@Test
	public void addOrdersToRoute() throws Exception {
		
		Configurator config = Configurator.GetInstance(new MoanaSettings().getDefault());
		
		String routeId = UUID.randomUUID().toString();
		
		config.getBus().Send(new AddNewRoute(routeId, "depot"));
		
		QueryRoutesDetailsById query = new QueryRoutesDetailsById(Arrays.asList(new String[]{routeId}));
		
		assertTrue(routeId.equals(config.getRoutesReadModel().query(query).results.iterator().next().routeId));
		
		ArrayList<String> orders = new ArrayList<>();
		
		for(int cont =0 ; cont<5 ; cont++){
			orders.add("order_"+cont);
			config.getBus().Send(new UpdateOrderCommand("order_"+cont, "order_"+cont, "testClient", 10, "", "", 10.0, 10.0));
		}
				
		config.getBus().Send(new AddOrdersToRoute(routeId, orders));
		
		QueryRoutesDetailsByIdResult res = config.getRoutesReadModel().query(query);
		
		assertTrue(routeId.equals(res.results.iterator().next().routeId));
		
		for (String orderId : res.results.iterator().next().ordersServedIds) {
			assertTrue(orders.contains(orderId));
		}

	}

}
