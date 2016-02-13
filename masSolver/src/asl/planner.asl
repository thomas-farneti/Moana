// Agent planner in project masSolver

/* Initial beliefs and rules */

state(waitingOrders).

all_proposals_received
	:- .structure(bidSent(C))	&			// number of participants
    .count(proposal(_,_,_), NP) &           // number of proposes received
    .count(refusal, NR) 	&			// number of refusals received
    C = NP + NR.

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").

+!clean(C,D) : not proposal(A,_,_) & C==D.
+!clean(C,D) : proposal(A,_,B) | C\==D <-
	.print("Something went wrong!"); 
	-proposal(A,_,B)[source(A)];
	.send(A,tell,fail(B));
	!clean(C,D+1).

//Pianifico ordine ma solo se ho almeno un veicolo

+!planOrder(Id,Dimension) : not vehicle(_) <- .print("Vehicle Created").
@r1 [atomic]
+!planOrder(Id,Dimension) : vehicle(_) <-
	.print("Start PlanOrder Plan");
	-+state(waitingProposal,Id);
	.findall(VehicleId,vehicle(VehicleId),VS);
	.print(VS);
	.send(VS,tell,auctionOrder(Id,Dimension));
	.length(VS,C);
	+bidSent(C).
	
@r2 [atomic]
+!checkWinner : all_proposals_received & bidSent(B) <-
	-bidSent(_);
	.findall(offer(C,A),proposal(A,C,_),O);
	.min(O,offer(Wo,Wa));
	.print(O);
	!announce_result(O,Wa);
	.abolish(refusal(_));
	.length(O,D);
	!clean(B,D);
	+state(waitingOrders).

+!announce_result([],_) : not proposal(_).
+!announce_result([],_) : proposal(_) <- 
	-proposal(_);
	!announce_result([],_).
// announce to the winner
+!announce_result([offer(_,WAg)|T],WAg) <-
	.print("vincitore ",WAg);
	.send(WAg,tell,accept_proposal);
	-proposal(WAg,_,_)[source(WAg)];
  	!announce_result(T,WAg).
// announce to others
+!announce_result([offer(_,LAg)|T],WAg) <-
	.print("perdenti");
	.send(LAg,tell,reject_proposal);
	-proposal(LAg,_,_)[source(LAg)];
	!announce_result(T,WAg).	

+vehicle(Me) <- .print("Welcome to ", Me).

+order(Id,Dimension)[source(percept)] : state(waitingOrders) & not proposal(_) <- 
	-state(waitingOrders);
	-order(Id,Dimension)[source(percept)];
	.print("Plan Order");
	!planOrder(Id,Dimension).
	
+proposal(_,_,_) : all_proposals_received & state(waitingProposal,_) <- 
	-state(waitingProposal,_);
	!checkWinner.