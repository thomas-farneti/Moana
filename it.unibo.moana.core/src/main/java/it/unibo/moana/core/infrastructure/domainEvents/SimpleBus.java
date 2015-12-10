package it.unibo.moana.core.infrastructure.domainEvents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import it.unibo.moana.messages.ICommand;
import it.unibo.moana.messages.IEvent;
import it.unibo.moana.messages.IMessage;

public class SimpleBus implements IBus {

	private HashMap<Class<?>, ArrayList<Consumer<IMessage>>> routes = new HashMap<>();
	
	public SimpleBus(){
		
	}
	@Override
	public <T extends ICommand> void Send(T command) throws Exception {
        ArrayList<Consumer<IMessage>> handlers;
        
        if (routes.containsKey(command.getClass()))
        {
        	handlers = routes.get(command.getClass());
            if (handlers.size() != 1) throw new Exception("cannot send to more than one handler");
            handlers.get(0).accept(command);
        }
        else
        {
            throw new Exception("no handler registered");
        }
	}

	@Override
	public <T extends IEvent> void Publish(T event) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IMessage> void RegisterHandler(Consumer<T> handler, Class<T> type) {
        ArrayList<Consumer<IMessage>> handlers;

        if(!routes.containsKey(type)){
        	handlers = new ArrayList<Consumer<IMessage>>();
        	routes.put(type, handlers);
        }else{
        	handlers = routes.get(type);
        }
        
        handlers.add(x -> handler.accept((T)x));
	}
	@Override
	public HashMap<Class<?>, ArrayList<Consumer<IMessage>>> getAllRoutes() {
		return routes;
	}

}
