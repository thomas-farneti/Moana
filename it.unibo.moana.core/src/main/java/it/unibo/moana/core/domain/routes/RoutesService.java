package it.unibo.moana.core.domain.routes;

import com.google.common.eventbus.Subscribe;

import it.unibo.moana.core.domain.loadingUnloadingPoints.ILoadingUnloadingPointRepository;
import it.unibo.moana.core.domain.loadingUnloadingPoints.LoadingUnloadingPoint;
import it.unibo.moana.core.domainEvents.IEventPublisher;
import it.unibo.moana.core.domainEvents.IHandler;
import it.unibo.moana.messages.routes.commands.AddNewRoute;

public class RoutesService implements IHandler {

	protected IRoutesRepository repo;
	protected IEventPublisher bus;
	protected ILoadingUnloadingPointRepository pointsRepo;
	
	public RoutesService(IRoutesRepository repo, ILoadingUnloadingPointRepository pointsRepo, IEventPublisher bus) {
		this.repo = repo;
		this.bus = bus;
		this.pointsRepo = pointsRepo;
	}
	
	@Subscribe
	public void handle(AddNewRoute cmd){
		
		LoadingUnloadingPoint depot = this.pointsRepo.load(cmd.depotId);
		
		if(repo.load(cmd.routeId)==null){
			repo.addOrUpdate(new Route(cmd.routeId,depot));
		}
	}
	
}
