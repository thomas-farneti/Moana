package it.unibo.moana.core.domain.domainServices.distanceTimeService;

import it.unibo.moana.core.domain.valueObjects.Position;

public interface IDistanceTimeService {
	IDistanceTimeResults computeDistanceTime(Iterable<Position> points);
}
