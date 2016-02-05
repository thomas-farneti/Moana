package it.unibo.moana.core.infrastructure.domainEvents;

import it.unibo.moana.messages.IMessage;

public interface IBus extends ICommandSender, IEventPublisher{

	<T extends IMessage> void registerHandler(IHandler handler);
	
}
