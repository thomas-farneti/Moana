package it.unibo.moana.messages.routes.query;

import java.util.Collection;

public class QueryRoutesDetailsById {
	public Collection<String> routeIds;

	public QueryRoutesDetailsById(Collection<String> routeIds) {
		this.routeIds = routeIds;
	}
}
