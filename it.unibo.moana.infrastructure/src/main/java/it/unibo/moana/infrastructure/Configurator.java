package it.unibo.moana.infrastructure;

import it.unibo.moana.core.domain.loadingUnloadingPoints.LoadingUnloadingPoint;
import it.unibo.moana.core.domain.orders.IOrdersReadModel;
import it.unibo.moana.core.domain.orders.IOrdersRepository;
import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.core.domain.orders.OrdersService;
import it.unibo.moana.core.domain.routes.IRoutesReadModel;
import it.unibo.moana.core.domain.routes.IRoutesRepository;
import it.unibo.moana.core.domain.routes.Route;
import it.unibo.moana.core.domain.valueObjects.Position;
import it.unibo.moana.infrastructure.bus.GuavaEventBus;
import it.unibo.moana.infrastructure.bus.IBus;
import it.unibo.moana.persistence.FakeRepository;
import it.unibo.moana.persistence.orders.OrdersReadModel;
import it.unibo.moana.persistence.orders.OrdersRepository;
import it.unibo.moana.persistence.routes.RoutesReadModel;
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
		routesReadModel = new RoutesReadModel(routesRepo, ordersRepo);
		
		eventBus.registerHandler(new OrdersService(ordersRepo, eventBus));
	}
	
	public static Configurator GetInstance(){
		if(config == null){
			config = new Configurator();
			addFakeValues();
			
		}
		
		return config;
	}
	
	private static void addFakeValues(){
		Order o = new Order("testOrder", "TestOrder",0, new LoadingUnloadingPoint("testClient", "testClient", Position.empty()));
		config.ordersRepo.addOrUpdate(o);
		
		Route r = new Route("testRoute");
		r.addOrderSequential(o);
		config.routesRepo.addOrUpdate(r);
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
