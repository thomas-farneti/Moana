package it.unibo.moana.core.domain.Ordini;

import com.google.common.eventbus.Subscribe;

import it.unibo.moana.core.infrastructure.domainEvents.IEventPublisher;
import it.unibo.moana.core.infrastructure.domainEvents.IHandler;
import it.unibo.moana.messages.ordini.commands.AggiornaOrdineCmd;
import it.unibo.moana.messages.ordini.events.OrdineAggiornatoEvent;

public class OrdiniService implements IHandler{
	
	@SuppressWarnings("unused")
	private IOrdineRepository repo;
	private IEventPublisher publisher;
	
	public OrdiniService(IOrdineRepository repo, IEventPublisher publisher){
		this.repo = repo;
		this.publisher = publisher;
	}
	
	@Subscribe
	public void Handle(AggiornaOrdineCmd cmd){
		System.out.println("Handle Command");
		publisher.Publish(new OrdineAggiornatoEvent());
	}
}
