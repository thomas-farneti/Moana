package it.unibo.moana.test.domain;

import org.junit.Test;

import it.unibo.moana.core.domain.Orders.OrdersService;
import it.unibo.moana.core.infrastructure.domainEvents.GuavaEventBus;
import it.unibo.moana.core.infrastructure.domainEvents.IBus;
import it.unibo.moana.messages.factories.UpdateOrderCommandFactory;

public class EventBus_Should {

	@Test
	public void test() throws Exception {	 		
		IBus bus = new GuavaEventBus();
		
		OrdersService svc = new  OrdersService(null, bus);
		
		bus.registerHandler(svc);
		
		bus.Send(UpdateOrderCommandFactory.getInstance("command description", 80.0, "1cli", "client descritpion", 1, 1));
	}
}