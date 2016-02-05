package it.unibo.moana.core.domain.Orders;

import java.util.Enumeration;

public interface IOrderRepository{
	void remove(Order entity);
	 void addOrUpdate(Order entity); 
	 
	 Order[] load(Enumeration<String> ids);
	 Order load(String id);
}
