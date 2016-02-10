package it.unibo.moana.core.infrastructure.persistence;

import java.util.Collection;

import it.unibo.moana.core.domain.IEntity;

public interface IRepository <K,E extends IEntity<K>>{

	 void remove(E entity);
	 void addOrUpdate(E entity); 
	 
	 Collection<E> load(Collection<K> ids);
	 E load(K id);
}
