// Internal action code for project masSolver

package it.unibo.masSolver.internalActions;

import it.unibo.moana.core.domain.routes.IRoutesReadModel;
import it.unibo.moana.infrastructure.Configurator;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCost;
import jason.asSemantics.*;
import jason.asSyntax.*;

@SuppressWarnings("serial")
public class computeInsertionCost extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        // execute the internal action
        ts.getAg().getLogger().info("executing internal action 'it.unibo.masSolver.internalActions.computeInsertionCost'");
        
        IRoutesReadModel rm = Configurator.GetInstance().getRoutesReadModel();
        
        StringTerm routeId = (StringTerm) args[0];
        StringTerm orderId = (StringTerm) args[1];
        
        //QueryOrderInsertionCost q = new QueryOrderInsertionCost(routeId.getString(),orderId.getString());
        
        NumberTerm result = new NumberTermImpl(rm.query(null).cost);
        
        return un.unifies(result,args[2]);
    }
}
