// Internal action code for project masSolver

package it.unibo.masSolver.internalActions;

import it.unibo.moana.core.domain.routes.IRoutesReadModel;
import it.unibo.moana.infrastructure.Configurator;
import it.unibo.moana.infrastructure.MoanaSettings;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCost;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCostResult;
import jason.asSemantics.*;
import jason.asSyntax.*;

@SuppressWarnings("serial")
public class computeInsertionCost extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        // execute the internal action
        ts.getAg().getLogger().info("executing internal action 'it.unibo.masSolver.internalActions.computeInsertionCost'");
        
        IRoutesReadModel rm = Configurator.GetInstance(new MoanaSettings().getDefault()).getRoutesReadModel();
        
        StringTerm routeId = (StringTerm) args[0];
        StringTerm orderId = (StringTerm) args[1];
        
        QueryOrderInsertionCost q = new QueryOrderInsertionCost(routeId.getString(),orderId.getString());
        
        Thread.sleep(1000);
        QueryOrderInsertionCostResult res =  rm.query(q);
        
        NumberTerm result = new NumberTermImpl(res.cost);
        
        return un.unifies(result,args[2]);
    }
}
