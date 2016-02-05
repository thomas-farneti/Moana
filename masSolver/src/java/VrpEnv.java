// Environment code for project masSolver

import java.util.logging.Logger;

import input.gui.OrdersGui;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import services.OrderHandler;
import it.unibo.moana.core.infrastructure.domainEvents.*;

public class VrpEnv extends Environment {

    private Logger logger = Logger.getLogger("masSolver."+VrpEnv.class.getName());
    private IBus eventBus;
    private OrderHandler orderHandler;
    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        
        clearAllPercepts();
        eventBus = new GuavaEventBus();
        orderHandler = new OrderHandler(this, null);
        eventBus.registerHandler(orderHandler);
        OrdersGui.LaunchGUI(eventBus);
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        logger.info("executing: "+action+", but not implemented!");
        return true;
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
