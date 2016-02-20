package it.unibo.moana.persistence.orders;

import java.util.Collection;

import it.unibo.moana.core.domain.orders.IOrdersRepository;
import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.persistence.IRepository;

public class MockOrdersRepository implements IOrdersRepository {

	private IRepository<String, Order> repository;
	
	public MockOrdersRepository(IRepository<String, Order> repository) {
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
	public Collection<Order> load(Collection<String> ids) {
		return repository.load(ids);
	}

	@Override
	public Order load(String id) {
		return repository.load(id);
	}
}
