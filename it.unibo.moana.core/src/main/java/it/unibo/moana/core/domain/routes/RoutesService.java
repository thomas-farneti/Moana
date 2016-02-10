package it.unibo.moana.core.domain.routes;

import it.unibo.moana.core.infrastructure.domainEvents.IEventPublisher;
import it.unibo.moana.core.infrastructure.domainEvents.IHandler;

public class RoutesService implements IHandler {

	protected IRoutesRepository repo;
	protected IEventPublisher bus;
	
	public RoutesService(IRoutesRepository repo, IEventPublisher bus) {
		this.repo = repo;
		this.bus = bus;
	}
	
	
	
}
