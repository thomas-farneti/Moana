package it.unibo.masSolver.env;
// Environment code for project masSolver

import java.net.UnknownHostException;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.google.common.eventbus.Subscribe;

import it.unibo.masSolver.output.ShowRoutesButtonHandler;
import it.unibo.masSolver.output.UpdateOrderHandler;
import it.unibo.masSolver.output.gui.OutputGui;
import it.unibo.moana.core.domainEvents.IHandler;
import it.unibo.moana.infrastructure.Configurator;
import it.unibo.moana.infrastructure.MoanaSettings;
import it.unibo.moana.infrastructure.bus.IBus;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import it.unibo.moana.messages.orders.events.OrderUpdatedEvent;
import it.unibo.moana.messages.routes.commands.AddNewRoute;
import it.unibo.moana.messages.routes.events.RouteUpdated;
import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;

public class VrpEnv extends Environment implements IHandler {

	private Logger logger = Logger.getLogger("masSolver."+VrpEnv.class.getName());
	protected Configurator config;
	private UpdateOrderHandler updateOrderHandler;
	private OutputGui gui;

	/** Called before the MAS execution with the args informed in .mas2j */
	@Override
	public void init(String[] args) {
		super.init(args);

		try {
			config = Configurator.GetInstance(new MoanaSettings().getDefault());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		updateOrderHandler = new UpdateOrderHandler();
		ShowRoutesButtonHandler buttonHandler = new ShowRoutesButtonHandler(updateOrderHandler, config.getOrdersReadModel(),super.getLogger());
		gui = new OutputGui(buttonHandler);
		buttonHandler.setTextArea(gui.getArea());
		
		config.getBus().registerHandler(this);
		config.getBus().registerHandler(updateOrderHandler);
		clearAllPercepts();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				gui.setVisible(true);
			}
		});
	}

	@Subscribe
	public void handle(OrderUpdatedEvent event) {
		clearAllPercepts();
		this.addPercept(Literal.parseLiteral("order(\"" + event.orderId + "\"," + event.demand + ")"));
	}
	
	@Subscribe
	public void handle(RouteUpdated event) {
		for (String o : event.ordersServedIds) {
			super.getLogger().info("AGGIUNTO ORDINE"+o+" A: "+event.routeId);
		}
		
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
		} else if (action.getFunctor().equals("addRoute")) {
			final String l = action.getTerm(0).toString().replace("\"",""); // get where to move
			try {
				bus.Send(new AddNewRoute(l, "depot"));
				result = true;
			} catch (Exception e) {
				result = false;
				e.printStackTrace();
			}
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
