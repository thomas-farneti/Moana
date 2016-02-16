package it.unibo.moana.core.domain.routes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.common.eventbus.Subscribe;

import it.unibo.moana.core.domain.loadingUnloadingPoints.ILoadingUnloadingPointRepository;
import it.unibo.moana.core.domain.loadingUnloadingPoints.LoadingUnloadingPoint;
import it.unibo.moana.core.domain.orders.IOrdersRepository;
import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.core.domainEvents.IEventPublisher;
import it.unibo.moana.core.domainEvents.IHandler;
import it.unibo.moana.messages.routes.commands.AddNewRoute;
import it.unibo.moana.messages.routes.commands.AddOrdersToNewRoute;
import it.unibo.moana.messages.routes.commands.AddOrdersToRoute;
import it.unibo.moana.messages.routes.events.RouteUpdated;

public class RoutesService implements IHandler {

	protected IRoutesRepository routesRepo;
	protected IEventPublisher bus;
	protected ILoadingUnloadingPointRepository pointsRepo;
	protected IOrdersRepository ordersRepo;
	
	public RoutesService(IRoutesRepository routesRepo, ILoadingUnloadingPointRepository pointsRepo,IOrdersRepository ordersRepo, IEventPublisher bus) {
		this.routesRepo = routesRepo;
		this.bus = bus;
		this.pointsRepo = pointsRepo;
		this.ordersRepo = ordersRepo;
	}
	
	@Subscribe
	public void handle(AddNewRoute cmd){
		
		LoadingUnloadingPoint depot = this.pointsRepo.load(cmd.depotId);
		
		if(routesRepo.load(cmd.routeId)==null){
			routesRepo.addOrUpdate(new Route(cmd.routeId,depot));
		}
	}
	
	@Subscribe
	public void handle(AddOrdersToNewRoute cmd){
		List<Order> orders = ordersRepo.load(cmd.ordersIds).stream().collect(Collectors.toList());
		
		LoadingUnloadingPoint depot = this.pointsRepo.load(cmd.depotId);
		
		if(!orders.isEmpty() && depot != null){
			String id = cmd.routeId;
			
			if(id == null || id.isEmpty()){
				id = UUID.randomUUID().toString();
			}
			
			Route r = new Route(id, depot);
			
			orders.stream().forEach(o-> {r.addOrderSequential(o); System.out.println(o);});
			
			this.routesRepo.addOrUpdate(r);
			
			this.bus.Publish(new RouteUpdated(id, cmd.ordersIds));
		}
	}
	
	@Subscribe
	public void handle(AddOrdersToRoute cmd){
		List<Order> orders = ordersRepo.load(cmd.ordersIds).stream().collect(Collectors.toList());
		
		Route r = this.routesRepo.load(cmd.routeId);
		
		if(!orders.isEmpty() && r != null){
			String id = cmd.routeId;
			
			orders.stream().forEach(o-> {r.addOrderSequential(o); System.out.println(o);});
			
			this.routesRepo.addOrUpdate(r);
			
			this.bus.Publish(new RouteUpdated(id, cmd.ordersIds));
		}
	}
	
}
