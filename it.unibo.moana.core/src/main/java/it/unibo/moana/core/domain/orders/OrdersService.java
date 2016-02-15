package it.unibo.moana.core.domain.orders;

import com.google.common.eventbus.Subscribe;

import it.unibo.moana.core.domain.loadingUnloadingPoints.ILoadingUnloadingPointRepository;
import it.unibo.moana.core.domain.loadingUnloadingPoints.LoadingUnloadingPoint;
import it.unibo.moana.core.domain.valueObjects.Dimension;
import it.unibo.moana.core.domainEvents.IEventPublisher;
import it.unibo.moana.core.domainEvents.IHandler;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import it.unibo.moana.messages.orders.events.OrderUpdatedEvent;

public class OrdersService implements IHandler {

	private IOrdersRepository repo;
	private ILoadingUnloadingPointRepository loadUnloadRepo;
	private IEventPublisher publisher;

	public OrdersService(IOrdersRepository repo, ILoadingUnloadingPointRepository loadUnloadRepo,
			IEventPublisher publisher) {
		this.repo = repo;
		this.publisher = publisher;
		this.loadUnloadRepo = loadUnloadRepo;
	}

	@Subscribe
	public void handle(UpdateOrderCommand cmd) {
		Order o = repo.load(cmd.id);
		LoadingUnloadingPoint point = loadUnloadRepo.load(cmd.clientId);

		if (o == null) {
			o = new Order(cmd.id, cmd.description,
					new Dimension(cmd.dimensionType, cmd.dimensionValue, cmd.dimensionMeasure), point);
			repo.addOrUpdate(o);
		}

		publisher.Publish(new OrderUpdatedEvent(cmd.id, cmd.dimensionValue, cmd.clientId));
	}
}
