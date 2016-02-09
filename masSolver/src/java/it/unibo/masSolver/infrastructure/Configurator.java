package it.unibo.masSolver.infrastructure;

import it.unibo.moana.core.domain.Orders.IOrdersReadModel;
import it.unibo.moana.core.domain.Orders.IOrdersRepository;
import it.unibo.moana.core.domain.Orders.Order;
import it.unibo.moana.core.domain.Orders.OrdersReadModel;
import it.unibo.moana.core.domain.Orders.OrdersService;
import it.unibo.moana.core.infrastructure.domainEvents.GuavaEventBus;
import it.unibo.moana.core.infrastructure.domainEvents.IBus;
import it.unibo.moana.persistence.FakeRepository;
import it.unibo.moana.persistence.orders.OrdersRepository;

public class Configurator {
	
	private static Configurator config;
	
	private IBus eventBus;
	
	private IOrdersRepository ordersRepo;
	private IOrdersReadModel ordersReadModel;
	
	
	private Configurator() {
		eventBus = new GuavaEventBus();
		
		ordersRepo = new OrdersRepository(new FakeRepository<String,Order>());
		ordersReadModel = new OrdersReadModel(ordersRepo);
		
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
}
