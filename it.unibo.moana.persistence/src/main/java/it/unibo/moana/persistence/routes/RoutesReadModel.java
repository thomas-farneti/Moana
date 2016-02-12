package it.unibo.moana.persistence.routes;

import java.io.Console;
import java.util.Arrays;

import it.unibo.moana.core.domain.orders.IOrdersReadModel;
import it.unibo.moana.core.domain.orders.IOrdersRepository;
import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.core.domain.routes.IRoutesReadModel;
import it.unibo.moana.core.domain.routes.IRoutesRepository;
import it.unibo.moana.core.domain.routes.Route;
import it.unibo.moana.core.domain.valueObjects.Position;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIds;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIdsResult.OrderDetails;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCost;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCostResult;

public class RoutesReadModel implements IRoutesReadModel {

	protected IRoutesRepository routesRepo;
	protected IOrdersRepository ordersRepo;
	
	public RoutesReadModel(IRoutesRepository routesRepo, IOrdersRepository ordersRepo) {
		this.routesRepo = routesRepo;
		this.ordersRepo = ordersRepo;
	}


	@Override
	public QueryOrderInsertionCostResult query(QueryOrderInsertionCost query) {
		Route r = routesRepo.load(query.routeId);
		
		Order o = ordersRepo.load(query.orderId);
		
		double cost =  r.computeOrderInsertionCost(o.getDestination().getPosition(),null);
		
		QueryOrderInsertionCostResult result = new QueryOrderInsertionCostResult();
		result.cost = cost;
		result.routeId = query.routeId;
		
		return result;
	}

}
