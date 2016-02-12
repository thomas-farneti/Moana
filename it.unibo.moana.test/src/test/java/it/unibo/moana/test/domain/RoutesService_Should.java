package it.unibo.moana.test.domain;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Test;

import it.unibo.moana.infrastructure.Configurator;
import it.unibo.moana.messages.routes.commands.AddNewRoute;
import it.unibo.moana.messages.routes.query.QueryRoutesDetailsById;

public class RoutesService_Should {

	@Test
	public void test() throws Exception {
		Configurator config = Configurator.GetInstance();
		
		String id = UUID.randomUUID().toString();
		
		config.getBus().Send(new AddNewRoute(id, "depot"));
		
		QueryRoutesDetailsById query = new QueryRoutesDetailsById(Arrays.asList(new String[]{id}));
		
		assertTrue(id == config.getRoutesReadModel().query(query).results.iterator().next().routeId);
	}

}
