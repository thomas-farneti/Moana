package it.unibo.moana.core.domain.Orders;

import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIds;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIdsResult;

public interface IOrdersReadModel {
	GetOrdersDetailsByIdsResult Query(GetOrdersDetailsByIds query);
}
