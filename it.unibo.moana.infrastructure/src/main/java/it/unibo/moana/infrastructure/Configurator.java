package it.unibo.moana.infrastructure;

import java.net.UnknownHostException;

import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.UpdateStrategies;
import org.mongolink.domain.mapper.ContextBuilder;

import com.google.common.collect.Lists;
import com.mongodb.ServerAddress;

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
import it.unibo.moana.persistence.MockRepository;
import it.unibo.moana.persistence.loadingUnloadingPoints.MockLoadingUnloadingPointsRepository;
import it.unibo.moana.persistence.loadingUnloadingPoints.MongoLoadUnloadPointsRepo;
import it.unibo.moana.persistence.mongo.MongoConfigurator;
import it.unibo.moana.persistence.orders.MockOrdersRepository;
import it.unibo.moana.persistence.orders.MongoOrdersRepository;
import it.unibo.moana.persistence.orders.OrdersReadModel;
import it.unibo.moana.persistence.routes.MockRoutesRepository;
import it.unibo.moana.persistence.routes.MongoRoutesRepository;
import it.unibo.moana.persistence.routes.RoutesReadModel;

public class Configurator {
	
	private static Configurator config;
	
	private IBus eventBus;
	
	private IOrdersRepository ordersRepo;
	private IOrdersReadModel ordersReadModel;
	private IRoutesRepository routesRepo;
	private IRoutesReadModel routesReadModel;
	private ILoadingUnloadingPointRepository loadingUnloadinPointRepo;
	private MongoSessionManager sessionManager;
	
	
	private Configurator(MoanaSettings settings) throws UnknownHostException {
		eventBus = new GuavaEventBus();
		
		if(settings.mongoPersistence){
			sessionManager = MongoConfigurator.getSessionManager();
			ordersRepo = new MongoOrdersRepository(sessionManager);
			routesRepo = new MongoRoutesRepository(sessionManager);
			loadingUnloadinPointRepo = new MongoLoadUnloadPointsRepo(sessionManager);
		}
		else{
			ordersRepo = new MockOrdersRepository(new MockRepository<String,Order>());
			routesRepo = new MockRoutesRepository(new MockRepository<String,Route>());
			loadingUnloadinPointRepo = new MockLoadingUnloadingPointsRepository(new MockRepository<String,LoadingUnloadingPoint>());
		}
		
		
		ordersReadModel = new OrdersReadModel(ordersRepo);
		routesReadModel = new RoutesReadModel(routesRepo, ordersRepo);
				
		eventBus.registerHandler(new OrdersService(ordersRepo,loadingUnloadinPointRepo, eventBus));
		eventBus.registerHandler(new RoutesService(routesRepo, loadingUnloadinPointRepo, ordersRepo, eventBus));
	}
	
	public static Configurator GetInstance(MoanaSettings settings) throws UnknownHostException{
		if(config == null){
			config = new Configurator(settings);
			
			if(!settings.mongoPersistence)
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

	public IOrdersRepository getOrdersRepo() {
		return ordersRepo;
	}

	public IRoutesRepository getRoutesRepo() {
		return routesRepo;
	}

	public ILoadingUnloadingPointRepository getLoadingUnloadinPointRepo() {
		return loadingUnloadinPointRepo;
	}

	public MongoSessionManager getSessionManager() {
		return sessionManager;
	}
	
}
