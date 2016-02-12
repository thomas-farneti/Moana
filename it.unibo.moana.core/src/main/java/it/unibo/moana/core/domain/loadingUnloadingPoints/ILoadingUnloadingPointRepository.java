package it.unibo.moana.core.domain.loadingUnloadingPoints;

import java.util.Collection;

public interface ILoadingUnloadingPointRepository {

	void remove(LoadingUnloadingPoint entity);

	void addOrUpdate(LoadingUnloadingPoint entity);

	Collection<LoadingUnloadingPoint> load(Collection<String> ids);

	LoadingUnloadingPoint load(String id);

}
