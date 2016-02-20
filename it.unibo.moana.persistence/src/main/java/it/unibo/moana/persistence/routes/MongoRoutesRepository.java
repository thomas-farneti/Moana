package it.unibo.moana.persistence.routes;

import java.net.UnknownHostException;

import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;

import it.unibo.moana.core.domain.routes.IRoutesRepository;
import it.unibo.moana.core.domain.routes.Route;
import it.unibo.moana.persistence.mongo.MongoRepository;

public class MongoRoutesRepository extends MongoRepository<String, Route> implements IRoutesRepository{

	public MongoRoutesRepository(MongoSessionManager session) throws UnknownHostException {
		super(session);
	}

	@Override
	public void addOrUpdate(Route entity) {
		MongoSession s = sessionManager.createSession();
		s.start();
		
		Route o= s.get(entity.getId(), Route.class);
		
		if(o == null){
			s.save(entity);
		}else{
			o.updateRoute(entity);			
		}
		
		s.stop();
	}

}
