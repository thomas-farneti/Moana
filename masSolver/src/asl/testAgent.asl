// Agent testAgent in project masSolver

/* Initial beliefs and rules */

finish(V) :- V >= 2.

/* Initial goals */

!start(0).

/* Plans */

+!start(A) : not finish(A) <- 
	.print("hello world.");
	it.unibo.masSolver.internalActions.computeInsertionCost("testRoute","testOrder",C);
	.print(C);
	
	.print("Start Sending orders");
	.wait(100);
	addOrder;
	!!start(A+1).

+!start(V) : finish(V) <- .print("!!!!!!!!!!!!!!!!!!!!!!!!END OF ALL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!").