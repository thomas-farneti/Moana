package it.unibo.moana.test.domain;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import it.unibo.moana.core.domain.Orders.IOrdersReadModel;
import it.unibo.moana.core.domain.Orders.IOrdersRepository;
import it.unibo.moana.core.domain.Orders.Order;
import it.unibo.moana.core.domain.Orders.OrdersReadModel;
import it.unibo.moana.core.domain.Orders.OrdersService;
import it.unibo.moana.core.infrastructure.domainEvents.GuavaEventBus;
import it.unibo.moana.core.infrastructure.domainEvents.IBus;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIds;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIdsResult;
import it.unibo.moana.persistence.FakeRepository;
import it.unibo.moana.persistence.orders.OrdersRepository;

public class EventBus_Should {

	@Test
	public void test() throws Exception {	 		
		IBus bus = new GuavaEventBus();
		IOrdersRepository repo = new OrdersRepository(new FakeRepository<String,Order>());
		
		OrdersService svc = new  OrdersService(repo, bus);
		
		bus.registerHandler(svc);
		
		String id = UUID.randomUUID().toString();
		
		UpdateOrderCommand cmd = new UpdateOrderCommand(id, "test", 100.0, UUID.randomUUID().toString(), 10.0, 10.0);
		
		bus.Send(cmd);
		
		IOrdersReadModel rm = new OrdersReadModel(repo);
		
		GetOrdersDetailsByIds query = new GetOrdersDetailsByIds();
		query.Ids= Arrays.asList(new String[]{id});
		
		GetOrdersDetailsByIdsResult res =  rm.Query(query);
		
		Assert.assertTrue("OK", res.OrdersDetails[0].Id == id);
	}
}