package it.unibo.masSolver.infrastructure;

import it.unibo.moana.core.domain.orders.IOrdersReadModel;
import it.unibo.moana.core.domain.orders.IOrdersRepository;
import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.core.domain.orders.OrdersReadModel;
import it.unibo.moana.core.domain.orders.OrdersService;
import it.unibo.moana.core.domain.routes.IRoutesReadModel;
import it.unibo.moana.core.domain.routes.IRoutesRepository;
import it.unibo.moana.core.domain.routes.Route;
import it.unibo.moana.core.domain.routes.RoutesReadModel;
import it.unibo.moana.core.infrastructure.domainEvents.GuavaEventBus;
import it.unibo.moana.core.infrastructure.domainEvents.IBus;
import it.unibo.moana.persistence.FakeRepository;
import it.unibo.moana.persistence.orders.OrdersRepository;
import it.unibo.moana.persistence.routes.RoutesRepository;

public class Configurator {
	
	private static Configurator config;
	
	private IBus eventBus;
	
	private IOrdersRepository ordersRepo;
	private IOrdersReadModel ordersReadModel;
	private IRoutesRepository routesRepo;
	private IRoutesReadModel routesReadModel;
	
	private Configurator() {
		eventBus = new GuavaEventBus();
		
		ordersRepo = new OrdersRepository(new FakeRepository<String,Order>());
		ordersReadModel = new OrdersReadModel(ordersRepo);
		
		routesRepo = new RoutesRepository(new FakeRepository<String,Route>());
		routesReadModel = new RoutesReadModel(routesRepo, ordersReadModel);
		
		eventBus.registerHandler(new OrdersService(ordersRepo, eventBus));
	}
	
	public static Configurator GetInstance(){
		if(config == null){
			config = new Configurator();
		}
		
		return config;
	}
	
	public IBus getBus(){
		return this.eventBus;
	}
	
	public IOrdersReadModel getOrdersReadModel(){
		return this.ordersReadModel;
	}
	
	public IRoutesReadModel getRoutesReadModel(){
		return this.routesReadModel;
	}
}
