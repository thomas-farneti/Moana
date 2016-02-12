package it.unibo.moana.core.domain.routes;

import java.util.ArrayList;
import java.util.Random;

import it.unibo.moana.core.domain.IEntity;
import it.unibo.moana.core.domain.domainServices.distanceTimeService.IDistanceTimeService;
import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.core.domain.valueObjects.Position;

public class Route implements IEntity<String> {

	protected String id;
	protected ArrayList<Order> orders;
	protected double currentLoad;
		
	public Route(String id) {
		this.id = id;
		this.orders = new ArrayList<>();
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	public void addOrderSequential(Order o){
		this.orders.add(o);
		this.currentLoad+=o.getOrderDemand();
	}
	
	public double computeOrderInsertionCost(Position orderPos, IDistanceTimeService dt){
//		Iterable<Position> positions = Arrays.asList(new Position[]{ orders.get(orders.size()-1).getClient().getPosition(),orderPos});
//		
//		dt.computeDistanceTime(positions);
		
		return new Random().nextInt(999)+1;
	}

}
