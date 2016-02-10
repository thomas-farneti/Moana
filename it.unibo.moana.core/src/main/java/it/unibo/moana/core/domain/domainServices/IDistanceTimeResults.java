package it.unibo.moana.core.domain.domainServices;

import it.unibo.moana.core.domain.valueObjects.Position;

public interface IDistanceTimeResults {
	DistanceTime getDistanceTime(Position from, Position to);
}
