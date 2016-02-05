package it.unibo.moana.persistence.orders;

import java.util.Enumeration;

import it.unibo.moana.core.domain.Orders.IOrderRepository;
import it.unibo.moana.core.domain.Orders.Order;
import it.unibo.moana.core.infrastructure.persistence.IRepository;

public class OrdersRepository implements IOrderRepository {

	private IRepository<String, Order> repository;
	
	public OrdersRepository(IRepository<String, Order> repository) {
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
	public Order[] load(Enumeration<String> ids) {
		return repository.load(ids);
	}

	@Override
	public Order load(String id) {
		return repository.load(id);
	}
}
