package it.unibo.moana.persistence.orders;

import java.util.ArrayList;
import java.util.List;

import it.unibo.moana.core.domain.orders.IOrdersReadModel;
import it.unibo.moana.core.domain.orders.IOrdersRepository;
import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIds;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIdsResult;

public class OrdersReadModel implements IOrdersReadModel {

	protected IOrdersRepository repo;
	
	public OrdersReadModel(IOrdersRepository repo) {
		this.repo=repo;
	}
	@Override
	public GetOrdersDetailsByIdsResult Query(GetOrdersDetailsByIds query) {
		
		List<GetOrdersDetailsByIdsResult.OrderDetails> details = new ArrayList<>();
		GetOrdersDetailsByIdsResult res =  new GetOrdersDetailsByIdsResult();
		
		for (Order order : this.repo.load(query.Ids)) {
			GetOrdersDetailsByIdsResult.OrderDetails detail = res.new OrderDetails();
			detail.Id= order.getId();
			detail.demand = order.getDemand().getValue();
			details.add(detail);
		}
		
		res.OrdersDetails=details;
		
		return res;
	}

}
