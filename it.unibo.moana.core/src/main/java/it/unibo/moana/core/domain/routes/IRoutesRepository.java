package it.unibo.moana.core.domain.routes;

import java.util.Collection;

public interface IRoutesRepository {
	 void remove(Route entity);
	 void addOrUpdate(Route entity); 
	 
	 Collection<Route> load(Collection<String> ids);
	 Route load(String id);
}
