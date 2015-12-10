package it.unibo.moana.core.infrastructure.persistence;

public interface IRepository <T,V>{

	 void remove(T entity);
	 void addOrUpdate(T entity); 
	 
	 T[] load(V[] ids);
	 T load(V id);
}
