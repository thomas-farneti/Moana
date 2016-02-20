package it.unibo.moana.test.domain;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Test;

import it.unibo.moana.infrastructure.Configurator;
import it.unibo.moana.infrastructure.MoanaSettings;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCost;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCostResult;

public class ComputeCost_Should {

	@Test
	public void test() throws UnknownHostException {
		Configurator config = Configurator.GetInstance(new MoanaSettings().getTestDefault());
		
		QueryOrderInsertionCostResult res =  config.getRoutesReadModel().query(new QueryOrderInsertionCost("testRoute","testOrder"));
		
		assertNotNull(res);
	}

}
