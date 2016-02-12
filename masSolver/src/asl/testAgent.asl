// Agent testAgent in project masSolver

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- 
	//.print("hello world.");
	//it.unibo.masSolver.internaActions.computeInsertionCost("testRoute","testOrder",C);
	//.print(C);
	
	.print("Start Sending orders");
	.wait(2000);
	
	
	addOrder;
	.wait(2000);
	addOrder;
	.wait(2000);
	addOrder;
	.wait(2000);
	addOrder;
	.wait(2000).
