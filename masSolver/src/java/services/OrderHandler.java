package services;

import com.google.common.eventbus.Subscribe;
import converter.PrologConverter;
import it.unibo.moana.core.infrastructure.domainEvents.IHandler;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import jason.asSyntax.Literal;
import jason.environment.Environment;

public class OrderHandler implements IHandler{
	private Environment agentEnvironment;
	
	public OrderHandler(Environment ev) {
		agentEnvironment = ev;
	}
	
	@Subscribe
	public void handle(UpdateOrderCommand updateCommand){
		Literal percept = PrologConverter.toProlog(updateCommand);
		if (agentEnvironment.containsPercept(null))
			agentEnvironment.addPercept(percept);
	}
}
