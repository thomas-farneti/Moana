package it.unibo.moana.test.infrastructure;

import org.junit.Test;

import it.unibo.moana.core.domain.Ordini.OrdiniService;
import it.unibo.moana.core.infrastructure.domainEvents.IBus;
import it.unibo.moana.core.infrastructure.domainEvents.SimpleBus;
import it.unibo.moana.messages.ordini.AggiornaOrdine;

public class IBus_Should {

	@Test
	public void registraHandler() {
		IBus bus = new SimpleBus();
		
		OrdiniService os = new OrdiniService(null);
		bus.RegisterHandler(x -> os.handle(x), AggiornaOrdine.class);
		
		
	}

}
