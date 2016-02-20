package it.unibo.moana.core.domain.valueObjects;

public class Dimension {
	protected String type;
	protected double value;
	protected String measure;
	
	protected Dimension() {};
	
	public Dimension(String type, double value, String measure) {
		super();
		this.type = type;
		this.value = value;
		this.measure = measure;
	}
	
	public static Dimension EMPTY(){
		return new Dimension("EMPTY",0,"#");
	}
	
	public void sum(Dimension value){
		if(value.measure.equals(measure))
			this.value+=value.value;
	}
	
	public void subtract(Dimension value){
		if(value.measure.equals(measure))
			this.value-=value.value;
	}
	
	public double getValue(){
		return this.value;
	}
}
