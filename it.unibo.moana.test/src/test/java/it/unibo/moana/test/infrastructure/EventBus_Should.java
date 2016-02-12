package it.unibo.moana.test.infrastructure;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import com.google.common.eventbus.Subscribe;

import it.unibo.moana.core.domain.orders.IOrdersRepository;
import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.core.domain.orders.OrdersService;
import it.unibo.moana.core.domainEvents.IHandler;
import it.unibo.moana.infrastructure.bus.GuavaEventBus;
import it.unibo.moana.infrastructure.bus.IBus;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import it.unibo.moana.messages.orders.events.OrderUpdatedEvent;
import it.unibo.moana.persistence.FakeRepository;
import it.unibo.moana.persistence.orders.OrdersRepository;

public class EventBus_Should implements IHandler {

	protected String orderIdEvent;
	@Test
	public void test() throws Exception {
		IBus bus = new GuavaEventBus();
		IOrdersRepository repo = new OrdersRepository(new FakeRepository<String,Order>());
		
		OrdersService svc = new  OrdersService(repo, bus);
		
		bus.registerHandler(svc);
		bus.registerHandler(this);
		
		String id = UUID.randomUUID().toString();
		
		UpdateOrderCommand cmd = new UpdateOrderCommand(id, "test", 100.0, UUID.randomUUID().toString(), 10.0, 10.0);
		
		bus.Send(cmd);
		
		Thread.sleep(500);
		
		assertTrue(id == this.orderIdEvent);
	}
	
	@Subscribe
	protected void handle(OrderUpdatedEvent e){
		this.orderIdEvent = e.orderId;
	}

}
