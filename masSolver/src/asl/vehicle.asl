// Agent vehicle in project masSolver

/* Initial beliefs and rules */
canChallengeOrder(VehicleCapacity,OrderCapacity) :- VehicleCapacity >= OrderCapacity.
full(Capacity) :- Capacity <= 10.
count(0).
vehicleCapacity(100).
/* Initial goals */

!start.

/* Plans */

+!start : .my_name(Me) <- 
	.print("hello world.", Me);
	.send(planner,tell,vehicle(Me)).

+auctionOrder(I,D)[source(planner)] : vehicleCapacity(V) & not canChallengeOrder(V,D) <-
	 .my_name(Me);
	.print("cannot serve the order ",I);
	.send(planner,tell,refusal(Me,I)).

+auctionOrder(I,D)[source(planner)] : vehicleCapacity(V) & canChallengeOrder(V,D) <- 
	.my_name(Me);
	it.unibo.masSolver.internalActions.computeInsertionCost("testRoute","testOrder",Cost);
	.send(planner,tell,proposal(Me,Cost,I)).

+accept_proposal[source(planner)] : 
	auctionOrder(I,D)[source(planner)] &
	count(C) & 
	vehicleCapacity(V) <-
	.print("I win for the order ", I);
	-accept_proposal[source(planner)];
	+route(C+1,order(I,D));
	-+count(C+1);
	-+vehicleCapacity(V-D);
	.abolish(order(I,D)[source(_)]);
	-auctionOrder(I,D)[source(planner)].
	
+reject_proposal[source(planner)] : auctionOrder(I,D)[source(planner)] <-
    .print("I loose for the order ", I);
    -reject_proposal[source(planner)];
    .abolish(order(I,D)[source(_)]);
	-auctionOrder(I,D)[source(planner)].
