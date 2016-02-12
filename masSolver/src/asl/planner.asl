// Agent planner in project masSolver

/* Initial beliefs and rules */

state(waitingOrders).

all_proposals_received
	:- .structure(bidSent(C))	&			// number of participants
    .count(proposal(_), NP) &           // number of proposes received
    .count(refusal, NR) 	&			// number of refusals received
    C = NP + NR.

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").

+order(Id,Dimension) : state(waitingOrders) <- 
	.print("Plan Order");
	-+state(plannig);
	!!planOrder(order(Id,Dimension)).
	
+order(Id,Dimension) : not state(waitingOrders) <- 
	.print("Stash order");
	+stash(order(Id,Dimension)).

//Pianifico ordine ma solo se ho almeno un veicolo

+!planOrder(order(Id,Dimension)) : not vehicle(_) <- .print("Crea veicolo").
+!planOrder(order(Id,Dimension)) : vehicle(_) <-
	.print("Start PlanOrder Plan");
	.findall(vehicle(Id),vechicle(Id),Vehicles);
	-+state(sendingBids);
	.send(Vehicles,tell,order(Id,Dimension));
	.count(vehicle(_),C);
	+bidSent(C);
	.print(C);
	-+state(waitingProposal).
	

+proposal(_,_) : all_proposals_received <-
	.findall(offer(C,A),proposal(A,C),O);
	.min(O,offer(Wo,Wa));
	.print("Winner",Wa);
	!announce_result(O,Wa);
	.abolish(refusal(_));
	.print("Stop").

+!announce_result([],_)<-true.
// announce to the winner
+!announce_result([offer(_,WAg)|T],WAg) <-
	.print("vincitori");
	.send(WAg,tell,accept_proposal);
	-proposal(WAg,_)[source(WAg)];
  	!announce_result(T,WAg).
// announce to others
+!announce_result(OrderID,[offer(_,LAg)|T],WAg) <-
	.print("perdenti");
	.send(LAg,tell,reject_proposal);
	-proposal(LAg,_)[source(LAg)];
	!announce_result(T,WAg).	
	
	
	