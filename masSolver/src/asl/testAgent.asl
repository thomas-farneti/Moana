// Agent testAgent in project masSolver

/* Initial beliefs and rules */

finish(V) :- V >= 200.

/* Initial goals */

!start(0).

/* Plans */

+!start(A) : not finish(A) <- 
	.print("test agent Start");
	.wait(500);
	//it.unibo.masSolver.internalActions.computeInsertionCost("testRoute","testOrder",C);
	//.print(C);
	
	.print("Start Sending orders");
	.wait(100);
	addOrder;
	!!start(A+1).

+!start(V) : finish(V) <- .print("!!!!!!!!!!!!!!!!!!!!!!!!END OF ALL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!").