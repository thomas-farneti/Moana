// Agent vehicle in project masSolver

/* Initial beliefs and rules */
canChallengeOrder(VehicleCapacity,OrderCapacity) :-
	not full(VehicleCapacity - OrderCapacity).
	
full(Capacity) :- threshold(V) &  Capacity <= V.
count(0).
threshold(0).
vehicleCapacity(100).
allAcceptedVehicleCapacity(100).
/* Initial goals */

!start.
!finish.
!startProcessingAuction.
/* Plans */

+!finish : not (vehicleCapacity(V) & full(V)) <- !finish.
@r 
+!finish[priority(20)] : vehicleCapacity(V) & full(V) <-
	.print("I AM FULL I CAN WRITE MY ROUTE DOWN AND LEAVE!");
	.my_name(Me);
	!createRoute(Me);
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

+!startProcessingAuction : not (auctionOrderProcessing(I,D) & not lock) <-
	!startProcessingAuction.

+!startProcessingAuction : auctionOrderProcessing(I,D) & not lock <-
	+lock;
	!testCapacityAndSend(I,D);
	!startProcessingAuction.
	
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
	-auctionOrderProcessing(I,D);
    -lock;
	.abolish(order(I,D)[source(_)]);
	.send(planner,tell,refusal(Me,I)).	 

+!winner(I) : auctionOrderProcessing(I,D) &
	count(C) &
	vehicleCapacity(V) &
	routeName(A) <-
	-+vehicleCapacity(V-D);
	-+allAcceptedVehicleCapacity(V-D);
	.print("I win for the order ", I);
	-accept_proposal(I)[source(planner)];
	+route(C+1,order(I,D));
	-auctionOrderProcessing(I,D);
	-lock;
	it.unibo.masSolver.internalActions.addOrderToRoute(A,[I]);
	-+count(C+1);
	.abolish(order(I,D)[source(percept)]).

+!looser(I):
	auctionOrderProcessing(I,D) &
	count(C) &
	vehicleCapacity(V) <-
    .print("I loose for the order ", I);
    -+allAcceptedVehicleCapacity(V);
    -reject_proposal(I)[source(planner)];
    -auctionOrderProcessing(I,D);
    -lock;
    .abolish(order(I,D)[source(percept)]).


+auctionOrder(I,D)[source(planner)] : true  <-
	-auctionOrder(I,D)[source(planner)];
	+auctionOrderProcessing(I,D).

+accept_proposal(Id)[source(planner)] <-
	!winner(Id).

+reject_proposal(Id)[source(planner)] <-
	!looser(Id).