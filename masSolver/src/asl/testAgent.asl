// Agent testAgent in project masSolver

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- 
	.print("hello world.");
	.wait(2000);
	.send(planner,tell,order(order1,50));
	.wait(400);
	.send(planner,tell,order(order2,40));
	.wait(300);
	.send(planner,tell,order(order3,20));
	.wait(900);
	.send(planner,tell,order(order4,70));
	.wait(100);
	.send(planner,tell,order(order5,50)).

+order(Id) : true <- .print("Recived ",Id).
