package it.unibo.moana.core.domainModel;

import it.unibo.moana.core.domainModel.valueObjects.Dimensione;

public class Ordine {

	private String id;
	
	private String descrizione;

	private Tappa[] tappe;

    private Dimensione[] dimensioni;

	public Ordine(){
		tappe = new Tappa[0];
		dimensioni = new Dimensione[0];
	}	
	
	public String getDescrizione() {
		return descrizione;
	}



	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	class Tappa{
		public Tappa(){
			
		}
	}
}
