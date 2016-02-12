package it.unibo.moana.infrastructure;

import it.unibo.moana.core.domain.loadingUnloadingPoints.ILoadingUnloadingPointRepository;
import it.unibo.moana.core.domain.loadingUnloadingPoints.LoadingUnloadingPoint;
import it.unibo.moana.core.domain.orders.IOrdersReadModel;
import it.unibo.moana.core.domain.orders.IOrdersRepository;
import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.core.domain.orders.OrdersService;
import it.unibo.moana.core.domain.routes.IRoutesReadModel;
import it.unibo.moana.core.domain.routes.IRoutesRepository;
import it.unibo.moana.core.domain.routes.Route;
import it.unibo.moana.core.domain.routes.RoutesService;
import it.unibo.moana.core.domain.valueObjects.Dimension;
import it.unibo.moana.core.domain.valueObjects.Position;
import it.unibo.moana.infrastructure.bus.GuavaEventBus;
import it.unibo.moana.infrastructure.bus.IBus;
import it.unibo.moana.persistence.FakeRepository;
import it.unibo.moana.persistence.loadingUnloadingPoints.LoadingUnloadingPointsRepository;
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
	private ILoadingUnloadingPointRepository loadingUnloadinPointRepo;
	
	private Configurator() {
		eventBus = new GuavaEventBus();
		
		ordersRepo = new OrdersRepository(new FakeRepository<String,Order>());
		ordersReadModel = new OrdersReadModel(ordersRepo);
		
		routesRepo = new RoutesRepository(new FakeRepository<String,Route>());
		routesReadModel = new RoutesReadModel(routesRepo, ordersRepo);
		
		loadingUnloadinPointRepo = new LoadingUnloadingPointsRepository(new FakeRepository<String,LoadingUnloadingPoint>());
		
		eventBus.registerHandler(new OrdersService(ordersRepo,loadingUnloadinPointRepo, eventBus));
		eventBus.registerHandler(new RoutesService(routesRepo, loadingUnloadinPointRepo, eventBus));
	}
	
	public static Configurator GetInstance(){
		if(config == null){
			config = new Configurator();
			addFakeValues();
			
		}
		
		return config;
	}
	
	private static void addFakeValues(){
		LoadingUnloadingPoint p = new LoadingUnloadingPoint("testClient", "testClient", Position.empty());
		config.loadingUnloadinPointRepo.addOrUpdate(p);
		LoadingUnloadingPoint depot = new LoadingUnloadingPoint("depot", "depot", new Position(0, 0));
		config.loadingUnloadinPointRepo.addOrUpdate(depot);
		
		Order o = new Order("testOrder", "TestOrder",new Dimension("Volume", 10, "m3"), new LoadingUnloadingPoint("testClient", "testClient", Position.empty()));
		config.ordersRepo.addOrUpdate(o);

		Route r = new Route("testRoute",depot);
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
