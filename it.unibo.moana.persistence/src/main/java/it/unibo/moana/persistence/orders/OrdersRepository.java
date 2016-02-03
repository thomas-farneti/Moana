package it.unibo.moana.persistence.orders;

import it.unibo.moana.core.domain.Orders.IOrderRepository;
import it.unibo.moana.core.domain.Orders.Order;
import it.unibo.moana.core.infrastructure.persistence.IRepository;

public class OrdersRepository implements IOrderRepository {

	private IRepository<Order, String> repository;
	
	public OrdersRepository(IRepository<Order, String> repository) {
		this.repository = repository;
	}

	@Override
	public void remove(Order entity) {
		repository.remove(entity);
	}

	@Override
	public void addOrUpdate(Order entity) {
		repository.addOrUpdate(entity);
	}

	@Override
	public Order[] load(String[] ids) {
		return repository.load(ids);
	}

	@Override
	public Order load(String id) {
		return repository.load(id);
	}
}
