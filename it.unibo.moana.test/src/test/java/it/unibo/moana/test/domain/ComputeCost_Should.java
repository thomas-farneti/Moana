package it.unibo.moana.test.domain;

import static org.junit.Assert.*;


import org.junit.Test;

import it.unibo.moana.infrastructure.Configurator;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCost;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCostResult;

public class ComputeCost_Should {

	@Test
	public void test() {
		Configurator config = Configurator.GetInstance();
		
		QueryOrderInsertionCostResult res =  config.getRoutesReadModel().query(new QueryOrderInsertionCost("testRoute","testOrder"));
		
		assertNotNull(res);
	}

}
