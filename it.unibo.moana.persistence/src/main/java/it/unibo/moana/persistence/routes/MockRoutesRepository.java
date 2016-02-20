package it.unibo.moana.persistence.routes;

import java.util.Collection;

import it.unibo.moana.core.domain.routes.IRoutesRepository;
import it.unibo.moana.core.domain.routes.Route;
import it.unibo.moana.persistence.IRepository;

public class MockRoutesRepository implements IRoutesRepository {

	IRepository<String, Route> repo;
	
	public MockRoutesRepository(IRepository<String, Route> repo) {
		this.repo = repo;
	}

	@Override
	public void remove(Route entity) {
		this.repo.remove(entity);
	}

	@Override
	public void addOrUpdate(Route entity) {
		this.repo.addOrUpdate(entity);
	}

	@Override
	public Collection<Route> load(Collection<String> ids) {
		return this.repo.load(ids);
	}

	@Override
	public Route load(String id) {
		return this.repo.load(id);
	}

}
