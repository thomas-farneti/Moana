// Agent vehicle in project masSolver

/* Initial beliefs and rules */

canChallengeOrder(VehicleCapacity,OrderCapacity) :- VehicleCapacity >= OrderCapacity.

vehicleCapacity(100).

/* Initial goals */

!start.

/* Plans */

+!start : .my_name(Me) <- 
	.print("Hello I'm ",Me);
	.send(planner,tell,vehicle(Me));
	.wait(2000);
	.send(planner,tell,order(100));.

+!testCuurentCapacity(Content) : vehicleCapacity(V) & canChallengeOrder(Content,V) <- .print("I Can challenge for the order").

+pendingOrder(Content) : true <- .print("Vehicle receved pending order");
	!testCuurentCapacity(Content);
	.print("Send back my proposal");
	.my_name(Me);
	/* Custom action for the computation of the Cost */
	.random(Cost);
	.send(planner, tell, proposalOrder(Content,Me,Cost)).
