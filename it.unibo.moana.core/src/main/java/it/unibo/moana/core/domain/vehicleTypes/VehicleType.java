package it.unibo.moana.core.domain.vehicleTypes;

import it.unibo.moana.core.domain.IEntity;
import it.unibo.moana.core.domain.valueObjects.Dimension;

public class VehicleType implements IEntity<String> {
	protected String id;
	protected Dimension capacity;
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
