// Internal action code for project masSolver

package it.unibo.masSolver.internalActions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.unibo.moana.infrastructure.Configurator;
import it.unibo.moana.infrastructure.MoanaSettings;
import it.unibo.moana.messages.routes.commands.AddOrdersToNewRoute;
import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;

public class addOrdersToNewRoute extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        ts.getAg().getLogger().info("Executing addOrdersToNewRoute");
        
        StringTerm routeId = (StringTermImpl) args[0];
        ListTerm ordersIdsListTerm = (ListTerm) args[1];
        
        Collection<String> ordersIds = new ArrayList<String>();
        
        for (Term t : ordersIdsListTerm.reverse().getAsList()) {
			StringTerm termId = (StringTermImpl) t;
			ordersIds.add(termId.getString());
			ts.getAg().getLogger().info(termId.getString());
		}
        
        Configurator.GetInstance(new MoanaSettings().getDefault()).getBus().Send(new AddOrdersToNewRoute(routeId.getString(),ordersIds,"depot"));
        
        return true;
    }
}
