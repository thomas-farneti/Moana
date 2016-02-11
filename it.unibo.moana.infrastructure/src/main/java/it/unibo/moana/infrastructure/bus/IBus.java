package it.unibo.moana.infrastructure.bus;

import it.unibo.moana.core.domainEvents.ICommandSender;
import it.unibo.moana.core.domainEvents.IEventPublisher;
import it.unibo.moana.core.domainEvents.IHandler;
import it.unibo.moana.messages.IMessage;

public interface IBus extends ICommandSender, IEventPublisher{

	<T extends IMessage> void registerHandler(IHandler handler);
	
}
