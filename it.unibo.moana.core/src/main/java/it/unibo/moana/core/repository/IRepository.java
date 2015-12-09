package it.unibo.moana.core.repository;

public interface IRepository <T,V>{
	 void add(T entity);
	 void remove(T entity);
	 void update(T entity); 
	 void store(T entity);
	 
	 T[] load(V[] ids);
	 T load(V id);
}
