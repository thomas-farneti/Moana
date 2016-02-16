// Agent vehicle in project masSolver

/* Initial beliefs and rules */
canChallengeOrder(VehicleCapacity,OrderCapacity) :- VehicleCapacity >= OrderCapacity.
full(Capacity) :- Capacity <= 10.
count(0).
vehicleCapacity(100).
/* Initial goals */

!start.
!finish.

/* Plans */

+!finish : not (vehicleCapacity(V) & full(V)) <- !finish.
+!finish : vehicleCapacity(V) & full(V) <-
	.print("I AM FULL I CAN WRITE MY ROUTE DOWN AND LEAVE!");
	.my_name(Me);
	.send(planner,tell,vehicleFull(Me));
	-routeName(_).

+!start : true <-
	.my_name(Me); 
	.print("hello world.", Me);
	!createRoute(Me);
	.send(planner,tell,vehicle(Me)).

+!createRoute(A) : routeName(_).
+!createRoute(A) : not routeName(_) <-
	.concat("",A,MeString);
	+routeName(MeString);
	addRoute(MeString).
	
+!computeCostAndSend(I) : .my_name(Me) & routeName(A) <- 
	it.unibo.masSolver.internalActions.computeInsertionCost(A,I,Cost);
	.send(planner,tell,proposal(Me,Cost,I)).

+auctionOrder(I,D)[source(planner)] : vehicleCapacity(V) & not canChallengeOrder(V,D) <-
	 .my_name(Me);
	.print("cannot serve the order ",I);
	-auctionOrder(I,D)[source(planner)];
	.abolish(order(I,D)[source(_)]);
	.send(planner,tell,refusal(Me,I)).

+auctionOrder(I,D)[source(planner)] : vehicleCapacity(V) & canChallengeOrder(V,D) & not routeName(A) <-
	.my_name(Me);
	!createRoute(Me);
	!computeCostAndSend(I).
+auctionOrder(I,D)[source(planner)] : vehicleCapacity(V) & canChallengeOrder(V,D) & routeName(A) <- 
	!computeCostAndSend(I).

+accept_proposal[source(planner)] : 
	auctionOrder(I,D)[source(planner)] &
	count(C) & 
	vehicleCapacity(V) &
	routeName(A) <-
	.print("I win for the order ", I);
	-accept_proposal[source(planner)];
	+route(C+1,order(I,D));
	it.unibo.masSolver.internalActions.addOrderToRoute(A,[I]);
	-+count(C+1);
	-+vehicleCapacity(V-D);
	.abolish(order(I,D)[source(_)]);
	-auctionOrder(I,D)[source(planner)].
	
+reject_proposal[source(planner)] : 
	auctionOrder(I,D)[source(planner)] &
	count(C) & 
	vehicleCapacity(V) <-
    .print("I loose for the order ", I);
    -reject_proposal[source(planner)];
    .abolish(order(I,D)[source(_)]);
	-auctionOrder(I,D)[source(planner)].
