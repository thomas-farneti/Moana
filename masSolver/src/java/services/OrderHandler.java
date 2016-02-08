package services;

import converter.PrologConverter;
import it.unibo.moana.core.domain.Orders.IOrdersRepository;
import it.unibo.moana.core.domain.Orders.Order;
import it.unibo.moana.core.infrastructure.domainEvents.IHandler;
import it.unibo.moana.messages.orders.events.OrderUpdatedEvent;
import jason.asSyntax.Literal;
import jason.environment.Environment;

public class OrderHandler implements IHandler{
	private Environment agentEnvironment;
	private IOrdersRepository repository;
	
	public OrderHandler(Environment ev, IOrdersRepository repository) {
		agentEnvironment = ev;
		this.repository = repository;
	}
	public void handle(OrderUpdatedEvent event){
		Order order = repository.load(event.getId());
		Literal percept = PrologConverter.toProlog(order);
		if (agentEnvironment.containsPercept(null))
			agentEnvironment.addPercept(percept);
	}
}
