package it.unibo.moana.test.domain;

import org.junit.Test;

import it.unibo.moana.core.domain.Ordini.OrdiniService;
import it.unibo.moana.core.infrastructure.domainEvents.GuavaEventBus;
import it.unibo.moana.core.infrastructure.domainEvents.IBus;
import it.unibo.moana.messages.ordini.commands.AggiornaOrdineCmd;

public class EventBus_Should {

	@Test
	public void test() throws Exception {	 
		
		IBus bus = new GuavaEventBus();
		
		OrdiniService svc = new  OrdiniService(null, bus);
		
		bus.registerHandler(svc);
		
		bus.Send(new AggiornaOrdineCmd());
		
	}

}
