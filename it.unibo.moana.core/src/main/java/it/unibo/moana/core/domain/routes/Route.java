package it.unibo.moana.core.domain.routes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

import it.unibo.moana.core.domain.IEntity;
import it.unibo.moana.core.domain.domainServices.distanceTimeService.IDistanceTimeService;
import it.unibo.moana.core.domain.loadingUnloadingPoints.LoadingUnloadingPoint;
import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.core.domain.valueObjects.Dimension;
import it.unibo.moana.core.domain.valueObjects.Position;

public class Route implements IEntity<String> {

	protected String id;
	protected ArrayList<Order> orders;
	protected Dimension currentLoad;
	protected LoadingUnloadingPoint depot;
	
	protected Route(){
		this.orders= new ArrayList<>();
		this.currentLoad= Dimension.EMPTY();
	}
	
	public Route(String id, LoadingUnloadingPoint depot) {
		this();
		this.id = id;
		this.depot = depot;
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	public void addOrderSequential(Order o){
		this.orders.add(o);
		this.currentLoad.sum(o.getDemand());
	}
	
	public double computeOrderInsertionCost(Position orderPos, IDistanceTimeService dt){
//		Iterable<Position> positions = Arrays.asList(new Position[]{ orders.get(orders.size()-1).getClient().getPosition(),orderPos});
//		
//		dt.computeDistanceTime(positions);
		
		return new Random().nextInt(999)+1;
	}
	
	public Collection<String> getOrdersServedIds(){
		return this.orders.stream().map(o -> o.getId()).collect(Collectors.toList());
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public Dimension getCurrentLoad() {
		return currentLoad;
	}

	public LoadingUnloadingPoint getDepot() {
		return depot;
	}

	public void updateRoute(Route r){
		this.currentLoad = r.currentLoad;
		this.depot = r.depot;
		this.orders = r.orders;
	}
	
}
