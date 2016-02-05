package it.unibo.moana.persistence;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

import it.unibo.moana.core.domain.Entity;
import it.unibo.moana.core.infrastructure.persistence.IRepository;

public class FakeRepository <K,E extends Entity<K>> implements IRepository<K,E> {

	protected HashMap<K,E> records;
	
	public FakeRepository() {
		records = new HashMap<>();
	}

	@Override
	public void remove(E entity) {
		records.remove(entity.getId());
	}

	@Override
	public void addOrUpdate(E entity) {
		records.put(entity.getId(), entity);
	}

	@Override
	public E[] load(Enumeration<K> ids) {
		return (E[]) records.entrySet().stream().filter(i -> Collections.list(ids).stream().anyMatch(x -> x == i.getKey())).map(y-> y.getValue()).toArray();
	}

	@Override
	public E load(K id) {
		return records.get(id);
	}
}
