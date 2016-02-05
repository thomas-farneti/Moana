package it.unibo.moana.core.infrastructure.persistence;

import java.util.Enumeration;

import it.unibo.moana.core.domain.Entity;

public interface IRepository <K,E extends Entity<K>>{

	 void remove(E entity);
	 void addOrUpdate(E entity); 
	 
	 E[] load(Enumeration<K> ids);
	 E load(K id);
}
