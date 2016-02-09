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
	.send(planner,tell,order(Me,100));.

+!testCurentCapacity(OrderID,Content) : vehicleCapacity(V) & canChallengeOrder(Content,V) <- .print("I Can challenge for the order").

+pendingOrder(OrderID,Content) : true <- .print("Vehicle receved pending order");
	!testCurentCapacity(OrderID,Content);
	.print("Send back my proposal");
	.my_name(Me);
	/* Custom action for the computation of the Cost */
	.random(Cost);
	.send(planner, tell, proposalOrder(OrderID,Me,Cost)).

+accept_proposal(OrderID) : true <- .print("I have to plan this shit ",OrderID ," and reduce my capacity").

+reject_proposal(OrderID) : true <- 
	.print("Planner SCREW YOU MOTHERFUCKER!!");
	-pendingOrder(OrderID,_)[source(planner)].