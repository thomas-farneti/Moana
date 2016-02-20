package it.unibo.moana.persistence;

import java.util.Collection;

import it.unibo.moana.core.domain.IEntity;

public interface IRepository <K,V extends IEntity<K>>{

	 void remove(V entity);
	 void addOrUpdate(V entity); 
	 
	 Collection<V> load(Collection<K> ids);
	 V load(K id);
}
