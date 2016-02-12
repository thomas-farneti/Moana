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
	
	public Route(String id, LoadingUnloadingPoint depot) {
		this.id = id;
		this.depot = depot;
		this.orders = new ArrayList<>();
		this.currentLoad = new Dimension("Volume", 0, "m3");
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	public void addOrderSequential(Order o){
		this.orders.add(o);
		this.currentLoad.sum(o.getOrderDemand());
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

}
