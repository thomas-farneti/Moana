package it.unibo.moana.core.infrastructure.domainEvents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import it.unibo.moana.messages.IMessage;

public interface IBus extends ICommandSender, IEventPublisher {

	<T extends IMessage> void RegisterHandler(Consumer<T> handler, Class<T> c);
	
	HashMap<Class<?>, ArrayList<Consumer<IMessage>>> getAllRoutes();
}
