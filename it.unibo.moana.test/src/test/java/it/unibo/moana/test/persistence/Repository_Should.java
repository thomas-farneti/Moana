package it.unibo.moana.test.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import it.unibo.moana.core.domain.orders.Order;
import it.unibo.moana.persistence.FakeRepository;
import it.unibo.moana.persistence.IRepository;

public class Repository_Should {

	@Test
	public void test() {
		IRepository<String, Order> repo = new FakeRepository<>();
		
		repo.addOrUpdate(new Order("test", "test", 10, null));
		
		Order o = repo.load("test");
		
		assertNotNull(o);
		assertTrue(o.getId()=="test");
	}

}
