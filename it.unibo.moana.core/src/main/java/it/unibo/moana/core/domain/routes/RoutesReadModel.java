package it.unibo.moana.core.domain.routes;

import java.util.Arrays;

import it.unibo.moana.core.domain.orders.IOrdersReadModel;
import it.unibo.moana.core.domain.valueObjects.Position;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIds;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIdsResult.OrderDetails;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCost;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCostResult;

public class RoutesReadModel implements IRoutesReadModel {

	protected IRoutesRepository rRepo;
	protected IOrdersReadModel ordersRm;
	
	public RoutesReadModel(IRoutesRepository rRepo, IOrdersReadModel ordersRm) {
		this.rRepo = rRepo;
		this.ordersRm = ordersRm;
	}


	@Override
	public QueryOrderInsertionCostResult query(QueryOrderInsertionCost query) {
		Route r = rRepo.load(query.routeId);
		
		GetOrdersDetailsByIds q = new GetOrdersDetailsByIds();
		q.Ids = Arrays.asList(new String[] {query.orderId});
		
		OrderDetails o = ordersRm.Query(q).OrdersDetails[0];
		
		double cost =  r.computeOrderInsertionCost(new Position(o.positionLatitude, o.positionLongitude));
		
		QueryOrderInsertionCostResult result = new QueryOrderInsertionCostResult();
		result.cost = cost;
		result.routeId = r.getId();
		
		return result;
	}

}
