package it.unibo.moana.persistence.loadingUnloadingPoints;

import java.util.Collection;

import it.unibo.moana.core.domain.loadingUnloadingPoints.ILoadingUnloadingPointRepository;
import it.unibo.moana.core.domain.loadingUnloadingPoints.LoadingUnloadingPoint;
import it.unibo.moana.persistence.IRepository;

public class MockLoadingUnloadingPointsRepository implements ILoadingUnloadingPointRepository{

	protected IRepository<String, LoadingUnloadingPoint> repo;
	
	public MockLoadingUnloadingPointsRepository(IRepository<String, LoadingUnloadingPoint> repo) {
		this.repo = repo;
	}

	@Override
	public void remove(LoadingUnloadingPoint entity) {
		this.repo.remove(entity);	
	}

	@Override
	public void addOrUpdate(LoadingUnloadingPoint entity) {
		this.repo.addOrUpdate(entity);
	}

	@Override
	public Collection<LoadingUnloadingPoint> load(Collection<String> ids) {
		return this.repo.load(ids);
	}

	@Override
	public LoadingUnloadingPoint load(String id) {
		return this.repo.load(id);
	}

}
