// Agent testAgent in project masSolver

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- 
	.print("hello world.");
	it.unibo.masSolver.internaActions.computeInsertionCost("testRoute","testOrder",C);
	.print(C);
	 
	 .my_name(Me);
	 .concat(Me,1,Order1);
	.send(planner,tell,order(Order1,50));
	.wait(2000);
	.concat(Me,2,Order2);
	.send(planner,tell,order(Order2,50));
	.wait(400);
	.concat(Me,3,Order3);
	.send(planner,tell,order(Order3,50));
	.wait(300);
	.concat(Me,4,Order4);
	.send(planner,tell,order(Order4,50)).
	/*.wait(900);
	.concat(Me,5,Order5);
	.send(planner,tell,order(Order5,50));
	.wait(100);
	.concat(Me,6,Order6);
	.send(planner,tell,order(Order6,50)).
*/
+order(Id) : true <- .print("Recived ",Id).
