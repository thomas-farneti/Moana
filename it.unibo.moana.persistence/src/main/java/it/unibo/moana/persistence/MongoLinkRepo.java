package it.unibo.moana.persistence;

import java.net.UnknownHostException;
import java.util.Collection;

import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.UpdateStrategies;
import org.mongolink.domain.mapper.ContextBuilder;

import com.google.common.collect.Lists;
import com.mongodb.ServerAddress;

import it.unibo.moana.core.domain.IEntity;

public class MongoLinkRepo<K,V extends IEntity<K>> implements IRepository<K, V>{

	protected MongoSessionManager mongoSessionManager;
	
	public MongoLinkRepo() throws UnknownHostException {
		ContextBuilder builder = new ContextBuilder("org.mongolink.example.persistence.mapping");
		Settings settings = Settings.defaultInstance()
		                    .withDefaultUpdateStrategy(UpdateStrategies.DIFF)
		                    .withDbName("db")
		                    .withAddresses(Lists.newArrayList(new ServerAddress("host", 7689)))
		                    .withAuthentication("user", "passwd");
		this.mongoSessionManager = MongoSessionManager.create(builder, settings);
	}
	
	@Override
	public void remove(V entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addOrUpdate(V entity) {
		MongoSession session= this.mongoSessionManager.createSession();
		
		session.start();
		
		session.save(entity);
		
		session.stop();		
	}

	@Override
	public Collection<V> load(Collection<K> ids) {
		MongoSession session= this.mongoSessionManager.createSession();
		
		session.start();
		
		session.stop();
		
		return null;
	}

	@Override
	public V load(K id) {
		// TODO Auto-generated method stub
		return null;
	}
}
