// Agent vehicle in project masSolver

/* Initial beliefs and rules */
canChallengeOrder(VehicleCapacity,OrderCapacity) :-
	threshold(V) & 
	((VehicleCapacity - V) >= OrderCapacity).
full(Capacity) :- threshold(V) &  Capacity <= V.
count(0).
threshold(10).
vehicleCapacity(100).
allAcceptedVehicleCapacity(100).
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

+!testCapacityAndSend(I,D) : 
		.my_name(Me) &
		 routeName(A) &
		 allAcceptedVehicleCapacity(V) &
		 canChallengeOrder(V,D) <-
	-+allAcceptedVehicleCapacity(V-D);
	it.unibo.masSolver.internalActions.computeInsertionCost(A,I,Cost);
	.send(planner,tell,proposal(Me,Cost,I)).

+!testCapacityAndSend(I,D) : 
		allAcceptedVehicleCapacity(V) & 
		not canChallengeOrder(V,D) <-
	.my_name(Me);
	.print("cannot serve the order ",I);
	-auctionOrder(I,D)[source(planner)];
	.abolish(order(I,D)[source(_)]);
	.send(planner,tell,refusal(Me,I)).	 

+auctionOrder(I,D)[source(planner)] : not routeName(A) <-
	.my_name(Me);
	!createRoute(Me);
	!testCapacityAndSend(I,D).
+auctionOrder(I,D)[source(planner)] : routeName(A) <-
	!testCapacityAndSend(I,D).

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
	-+allAcceptedVehicleCapacity(V-D);
	.abolish(order(I,D)[source(percept)]);
	-auctionOrder(I,D)[source(planner)].

+reject_proposal[source(planner)] :
	auctionOrder(I,D)[source(planner)] &
	count(C) &
	vehicleCapacity(V) <-
    .print("I loose for the order ", I);
    -+allAcceptedVehicleCapacity(V);
    -reject_proposal[source(planner)];
    .abolish(order(I,D)[source(percept)]);
    -auctionOrder(I,D)[source(planner)].
