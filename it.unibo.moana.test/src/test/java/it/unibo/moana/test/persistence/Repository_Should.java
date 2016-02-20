package it.unibo.moana.test.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.core.domain.valueObjects.Dimension;
import it.unibo.moana.persistence.MockRepository;
import it.unibo.moana.persistence.IRepository;

public class Repository_Should {

	@Test
	public void test() {
		IRepository<String, Order> repo = new MockRepository<>();
		
		repo.addOrUpdate(new Order("test", "test", new Dimension("Volume", 0, "m3"), null));
		
		Order o = repo.load("test");
		
		assertNotNull(o);
		assertTrue(o.getId()=="test");
	}

}
