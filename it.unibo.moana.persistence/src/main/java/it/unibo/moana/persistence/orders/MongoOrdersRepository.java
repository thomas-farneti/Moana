package it.unibo.moana.persistence.orders;

import java.net.UnknownHostException;

import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;

import it.unibo.moana.core.domain.orders.IOrdersRepository;
import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.persistence.mongo.MongoRepository;

public class MongoOrdersRepository extends MongoRepository<String, Order> implements IOrdersRepository{

	public MongoOrdersRepository(MongoSessionManager session) throws UnknownHostException {
		super(session);
	}

	@Override
	public void addOrUpdate(Order entity) {
		MongoSession s = sessionManager.createSession();
		s.start();
		
		Order o= s.get(entity.getId(), Order.class);
		
		if(o == null){
			s.save(entity);
		}else{
			o.updateOrder(entity);			
		}
		
		s.stop();
	}
}
