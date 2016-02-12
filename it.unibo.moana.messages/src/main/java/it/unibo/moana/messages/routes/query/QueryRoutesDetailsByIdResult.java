package it.unibo.moana.messages.routes.query;

import java.util.Collection;

public class QueryRoutesDetailsByIdResult {

	public Collection<RouteDetail> results;
	
	public class RouteDetail {
		public String routeId;
		public Collection<String> ordersServedIds;
	}
}
