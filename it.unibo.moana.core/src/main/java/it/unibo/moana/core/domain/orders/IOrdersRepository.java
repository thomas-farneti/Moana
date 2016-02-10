package it.unibo.moana.core.domain.orders;

import java.util.Collection;

public interface IOrdersRepository{
	void remove(Order entity);
	 void addOrUpdate(Order entity); 
	 
	 Collection<Order> load(Collection<String> ids);
	 Order load(String id);
}
