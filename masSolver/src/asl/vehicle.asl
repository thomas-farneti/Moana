// Agent vehicle in project masSolver

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- 
	.print("hello world.");
	.send(planner,tell,vehicle(Me)).
	
+order(I,D) : true 
	<-
	.my_name(Me); 
	.send(planner,tell,proposal(Me,10)).
