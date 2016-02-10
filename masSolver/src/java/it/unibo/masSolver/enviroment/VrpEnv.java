package it.unibo.masSolver.enviroment;
// Environment code for project masSolver

import java.util.UUID;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import it.unibo.masSolver.infrastructure.Configurator;
import it.unibo.moana.core.infrastructure.domainEvents.IBus;
import it.unibo.moana.core.infrastructure.domainEvents.IHandler;
import it.unibo.moana.messages.orders.commands.UpdateOrderCommand;
import it.unibo.moana.messages.orders.events.OrderUpdatedEvent;
import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;

public class VrpEnv extends Environment implements IHandler {

    //private Logger logger = Logger.getLogger("masSolver."+VrpEnv.class.getName());
    
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
    public void handle(OrderUpdatedEvent event){
    	this.addPercept("testAgent", Literal.parseLiteral("order(\""+event.getId()+"\")"));
    }
    
    @Override
    public boolean executeAction(String ag, Structure action) {
    	 System.out.println("[" + ag + "] doing: " + action);
         boolean result = false;
         if (action.equals(Literal.parseLiteral("test"))) {
             IBus bus = config.getBus();
             String id = UUID.randomUUID().toString();
     		
     		UpdateOrderCommand cmd = new UpdateOrderCommand(id, "test", 100.0, UUID.randomUUID().toString(), 10.0, 10.0);
     		
     		try {
				bus.Send(cmd);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
         }
        return result;
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
