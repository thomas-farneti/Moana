package it.unibo.moana.persistence.loadingUnloadingPoints;

import java.net.UnknownHostException;

import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;

import it.unibo.moana.core.domain.loadingUnloadingPoints.ILoadingUnloadingPointRepository;
import it.unibo.moana.core.domain.loadingUnloadingPoints.LoadingUnloadingPoint;
import it.unibo.moana.persistence.mongo.MongoRepository;

public class MongoLoadUnloadPointsRepo extends MongoRepository<String, LoadingUnloadingPoint> implements ILoadingUnloadingPointRepository{

	public MongoLoadUnloadPointsRepo(MongoSessionManager session) throws UnknownHostException {
		super(session);
	}

	@Override
	public void addOrUpdate(LoadingUnloadingPoint entity) {
		MongoSession s = sessionManager.createSession();
		s.start();
		
		LoadingUnloadingPoint o= s.get(entity.getId(), LoadingUnloadingPoint.class);
		
		if(o == null){
			s.save(entity);
		}else{
			o.updateLoadingUnloadingPoint(entity);			
		}
		
		s.stop();
	}

}
