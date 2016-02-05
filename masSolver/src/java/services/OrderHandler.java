package services;

import com.google.common.eventbus.Subscribe;
import converter.PrologConverter;
import it.unibo.moana.core.domain.Orders.IOrderRepository;
import it.unibo.moana.core.domain.Orders.Order;
import it.unibo.moana.core.infrastructure.domainEvents.IHandler;
import it.unibo.moana.core.infrastructure.persistence.IRepository;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import it.unibo.moana.messages.orders.events.OrderUpdatedEvent;
import jason.asSyntax.Literal;
import jason.environment.Environment;

public class OrderHandler implements IHandler{
	private Environment agentEnvironment;
	private IOrderRepository repository;
	
	public OrderHandler(Environment ev, IOrderRepository repository) {
		agentEnvironment = ev;
		this.repository = repository;
	}
	
	@Subscribe
	public void handle(OrderUpdatedEvent event){
		Order order = repository.load(event.getId());
		Literal percept = PrologConverter.toProlog(order);
		if (agentEnvironment.containsPercept(null))
			agentEnvironment.addPercept(percept);
	}
}
