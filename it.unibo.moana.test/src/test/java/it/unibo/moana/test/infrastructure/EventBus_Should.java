package it.unibo.moana.test.infrastructure;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import com.google.common.eventbus.Subscribe;

import it.unibo.moana.core.domain.orders.IOrdersReadModel;
import it.unibo.moana.core.domainEvents.IHandler;
import it.unibo.moana.infrastructure.Configurator;
import it.unibo.moana.infrastructure.MoanaSettings;
import it.unibo.moana.infrastructure.bus.IBus;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import it.unibo.moana.messages.orders.events.OrderUpdatedEvent;

public class EventBus_Should implements IHandler {

	protected String orderIdEvent;
	@Test
	public void test() throws Exception {
		Configurator config = Configurator.GetInstance(new MoanaSettings().getTestDefault());
		
		IBus bus = config.getBus();
		
		bus.registerHandler(this);
		
		String id = UUID.randomUUID().toString();
		
		UpdateOrderCommand cmd = new UpdateOrderCommand(id, id, "testClient", 0, "Volume", "m3", 10.0, 10.0);
		
		bus.Send(cmd);
		
		Thread.sleep(100);
		
		assertTrue(id == this.orderIdEvent);
	}
	
	@Subscribe
	protected void handle(OrderUpdatedEvent e){
		this.orderIdEvent = e.orderId;
	}

}
