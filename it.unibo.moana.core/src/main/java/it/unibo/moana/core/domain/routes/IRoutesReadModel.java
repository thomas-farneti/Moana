package it.unibo.moana.core.domain.routes;

import it.unibo.moana.messages.routes.query.QueryOrderInsertionCost;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCostResult;
import it.unibo.moana.messages.routes.query.QueryRoutesDetailsById;
import it.unibo.moana.messages.routes.query.QueryRoutesDetailsByIdResult;

public interface IRoutesReadModel  {
	QueryOrderInsertionCostResult query(QueryOrderInsertionCost query);
	
	QueryRoutesDetailsByIdResult query (QueryRoutesDetailsById query);
}
