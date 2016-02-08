package it.unibo.moana.core.domain.Orders;

import com.google.common.eventbus.Subscribe;

import it.unibo.moana.core.infrastructure.domainEvents.IEventPublisher;
import it.unibo.moana.core.infrastructure.domainEvents.IHandler;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import it.unibo.moana.messages.orders.events.OrderUpdatedEvent;

public class OrdersService implements IHandler{
	
	private IOrdersRepository repo;
	private IEventPublisher publisher;
	
	public OrdersService(IOrdersRepository repo, IEventPublisher publisher){
		this.repo = repo;
		this.publisher = publisher;
	}
	
	@Subscribe
	public void Handle(UpdateOrderCommand cmd){
		Order o = repo.load(cmd.id);
		
		if(o == null)
		{
			o = new Order(cmd.id, cmd.description, null);
			repo.addOrUpdate(o);
		}
		
		publisher.Publish(new OrderUpdatedEvent(cmd.id));
	}
}
