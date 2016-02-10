package it.unibo.moana.core.domain.routes;

import it.unibo.moana.messages.routes.query.QueryOrderInsertionCost;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCostResult;

public interface IRoutesReadModel  {
	QueryOrderInsertionCostResult query(QueryOrderInsertionCost query);
}
