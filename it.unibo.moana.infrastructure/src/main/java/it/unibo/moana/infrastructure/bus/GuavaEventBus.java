package it.unibo.moana.infrastructure.bus;

import com.google.common.eventbus.EventBus;

import it.unibo.moana.core.domainEvents.IHandler;
import it.unibo.moana.messages.ICommand;
import it.unibo.moana.messages.IEvent;
import it.unibo.moana.messages.IMessage;

public class GuavaEventBus implements IBus{

	protected static EventBus bus;
	
	public GuavaEventBus(){
		bus= new EventBus();
	}
	
	@Override
	public <T extends ICommand> void Send(T command) throws Exception {
		bus.post(command);
	}

	@Override
	public <T extends IEvent> void Publish(T event) {
		bus.post(event);
	}

	@Override
	public <T extends IMessage> void registerHandler(IHandler handler) {
		bus.register(handler);
	}
}
