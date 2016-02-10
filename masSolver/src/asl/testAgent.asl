// Agent testAgent in project masSolver

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- 
	.print("hello world.");
	.wait(2000);
	.send(planner,tell,order(order1,50));
	.wait(300);
	.send(planner,tell,order(order2,20));
	.wait(900);
	.send(planner,tell,order(order3,70)).

+order(Id) : true <- .print("Recived ",Id).
