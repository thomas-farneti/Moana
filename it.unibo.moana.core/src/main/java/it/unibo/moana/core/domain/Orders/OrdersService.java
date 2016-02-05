package it.unibo.moana.core.domain.Orders;

import com.google.common.eventbus.Subscribe;

import it.unibo.moana.core.infrastructure.domainEvents.IEventPublisher;
import it.unibo.moana.core.infrastructure.domainEvents.IHandler;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import it.unibo.moana.messages.orders.events.OrderUpdatedEvent;

public class OrdersService implements IHandler{
	
	@SuppressWarnings("unused")
	private IOrderRepository repo;
	private IEventPublisher publisher;
	
	public OrdersService(IOrderRepository repo, IEventPublisher publisher){
		this.repo = repo;
		this.publisher = publisher;
	}
	
	@Subscribe
	public void Handle(UpdateOrderCommand cmd){
		System.out.println("Handle Command");
		publisher.Publish(new OrderUpdatedEvent(null));
	}
}
