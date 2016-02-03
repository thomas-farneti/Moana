package it.unibo.moana.persistence.orders;

import it.unibo.moana.core.domain.Orders.Order;
import it.unibo.moana.core.infrastructure.persistence.IRepository;

public class BaseOrdersRepository implements IRepository<Order, String> {

	@Override
	public void remove(Order entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addOrUpdate(Order entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order[] load(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order load(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
