package it.unibo.moana.persistence;

import java.util.ArrayList;
import java.util.Collection;
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
	public Collection<E> load(Collection<K> ids) {
		Collection<E> result = new ArrayList<>();
		
		records.entrySet().stream().filter(e-> ids.contains(e.getKey())).map(m-> m.getValue()).forEach(v -> result.add(v));
		return result;
	}

	@Override
	public E load(K id) {
		return records.get(id);
	}
}
