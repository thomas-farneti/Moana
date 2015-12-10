package it.unibo.moana.persistence.ordini;

import it.unibo.moana.core.domain.Ordini.IOrdineRepository;
import it.unibo.moana.core.domain.Ordini.Ordine;
import it.unibo.moana.core.infrastructure.persistence.IRepository;

public class OrdiniRepository implements IOrdineRepository {
	protected IRepository<Ordine, String> repo;
	
	public OrdiniRepository(IRepository<Ordine, String> repo){
		this.repo = repo;
	}
}
