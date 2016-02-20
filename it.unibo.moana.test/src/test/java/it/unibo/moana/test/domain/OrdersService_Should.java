package it.unibo.moana.test.domain;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import it.unibo.moana.core.domain.orders.IOrdersReadModel;
import it.unibo.moana.infrastructure.Configurator;
import it.unibo.moana.infrastructure.MoanaSettings;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIds;
import it.unibo.moana.messages.orders.query.GetOrdersDetailsByIdsResult;

public class OrdersService_Should {

	@Test
	public void test() throws Exception {	 		

		Configurator config = Configurator.GetInstance(new MoanaSettings().getTestDefault());
		
		String id = UUID.randomUUID().toString();
		UpdateOrderCommand cmd = new UpdateOrderCommand(id, id, "testClient", 0, "Volume", "m3", 10.0, 10.0);
		config.getBus().Send(cmd);

		IOrdersReadModel rm = config.getOrdersReadModel();
		
		GetOrdersDetailsByIds query = new GetOrdersDetailsByIds();
		query.Ids= Arrays.asList(new String[]{id});
		
		GetOrdersDetailsByIdsResult res =  rm.Query(query);
		
		Assert.assertTrue("OK", res.OrdersDetails.iterator().next().Id == id);
	}
}