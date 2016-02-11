package it.unibo.moana.messages.routes.query;

public class QueryOrderInsertionCost {
	public String routeId;
	public String orderId;
	
	public QueryOrderInsertionCost(){}
	
	public QueryOrderInsertionCost(String routeId, String orderId) {
		this.routeId = routeId;
		this.orderId = orderId;
	}
}
