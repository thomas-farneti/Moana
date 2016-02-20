package it.unibo.moana.persistence.mongo;

import java.lang.reflect.ParameterizedType;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.stream.Collectors;

import org.mongolink.MongoSession;
import org.mongolink.MongoSessionManager;

import it.unibo.moana.core.domain.IEntity;
import it.unibo.moana.persistence.IRepository;

public abstract class MongoRepository<K,V extends IEntity<K>> implements IRepository<K, V>{

	protected MongoSessionManager sessionManager;

	public MongoRepository(MongoSessionManager sessionManager) throws UnknownHostException {
		this.sessionManager = sessionManager;
	}
	
	@Override
	public void remove(V entity) {
		MongoSession session = sessionManager.createSession();
		
		session.start();
		session.get(entity.getId(),persistentType());
		session.delete(entity);
		session.stop();
	}

	@Override
	public Collection<V> load(Collection<K> ids) {
		MongoSession session = sessionManager.createSession();
		
		session.start();
		Collection<V>  values = ids.stream().map(id -> session.get(id, persistentType())).collect(Collectors.toList());
		session.stop();
		
		return values;
	}

	@Override
	public V load(K id) {
		MongoSession session = sessionManager.createSession();
		
		session.start();
		V value = session.get(id, persistentType());
		session.stop();
		
		return value;
	}
	
    @SuppressWarnings("unchecked")
	protected final Class<V> persistentType() {
        final ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<V>) superclass.getActualTypeArguments()[1];
    }
}
