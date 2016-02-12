package it.unibo.masSolver.env;
// Environment code for project masSolver

import java.util.Random;
import java.util.UUID;

import com.google.common.eventbus.Subscribe;

import it.unibo.moana.core.domainEvents.IHandler;
import it.unibo.moana.infrastructure.Configurator;
import it.unibo.moana.infrastructure.bus.IBus;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import it.unibo.moana.messages.orders.events.OrderUpdatedEvent;
import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;

public class VrpEnv extends Environment implements IHandler {

	// private Logger logger =
	// Logger.getLogger("masSolver."+VrpEnv.class.getName());

	protected Configurator config;

	/** Called before the MAS execution with the args informed in .mas2j */
	@Override
	public void init(String[] args) {
		super.init(args);

		config = Configurator.GetInstance();

		config.getBus().registerHandler(this);
		clearAllPercepts();
	}

	@Subscribe
	public void handle(OrderUpdatedEvent event) {
		clearAllPercepts();
		this.addPercept(Literal.parseLiteral("order(\"" + event.orderId + "\"," + event.demand + ")"));
	}

	@Override
	public boolean executeAction(String ag, Structure action) {
		IBus bus = config.getBus();
		boolean result = false;

		if (action.equals(Literal.parseLiteral("addOrder"))) {

			System.out.println("[" + ag + "] doing: " + action);

			String id = UUID.randomUUID().toString();

			UpdateOrderCommand cmd = new UpdateOrderCommand(id, id, "testClient", (new Random().nextDouble() * 49) + 1,
					"Volume", "m3", (new Random().nextDouble() * 999) + 1, (new Random().nextDouble() * 999) + 1);
			try {
				bus.Send(cmd);
				result = true;
			} catch (Exception e) {
				System.out.println("[" + ag + "] exception: " + e.getMessage());
				e.printStackTrace();
			}
		} else if (action.getFunctor().equals(Literal.parseLiteral("addRoute"))) {
			result = false;
		} else if (action.getFunctor().equals(Literal.parseLiteral("addOrderToRoute"))) {
			result = false;
		}

		return result;
	}

	/** Called before the end of MAS execution */
	@Override
	public void stop() {
		super.stop();
	}
}