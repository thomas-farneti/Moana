package it.unibo.moana.core.domain.Orders;

public interface IOrderRepository{
	void remove(Order entity);
	void addOrUpdate(Order entity); 
	 
	Order[] load(String[] ids);
	Order load(String id);
}
