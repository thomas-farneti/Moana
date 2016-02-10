// Internal action code for project masSolver

package it.unibo.masSolver.internaActions;

import it.unibo.masSolver.infrastructure.Configurator;
import it.unibo.moana.core.domain.routes.IRoutesReadModel;
import it.unibo.moana.messages.routes.query.QueryOrderInsertionCost;
import jason.asSemantics.*;
import jason.asSyntax.*;

@SuppressWarnings("serial")
public class computeInsertionCost extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        // execute the internal action
        ts.getAg().getLogger().info("executing internal action 'it.unibo.masSolver.internaActions.computeInsertionCost'");
        
        IRoutesReadModel rm = Configurator.GetInstance().getRoutesReadModel();
        
        StringTerm routeId = (StringTerm) args[0];
        StringTerm orderId = (StringTerm) args[1];
        
        QueryOrderInsertionCost q = new QueryOrderInsertionCost();
        q.orderId = orderId.getString();
        q.routeId = routeId.getString();
        
        NumberTerm result = new NumberTermImpl(rm.query(q).cost);
        
        return un.unifies(result,args[2]);
    }
}
