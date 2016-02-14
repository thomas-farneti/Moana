// Agent planner in project masSolver

/* Initial beliefs and rules */

state(waitingOrders).

all_proposals_received
	:-  bidSent(C,E)		&			// number of participants
    	responseObtained(D,E) & 
    	state(waitingProposal,E) &
    	C==D.

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").

//Pianifico ordine ma solo se ho almeno un veicolo

+!planOrder(Id,Dimension) : not vehicle(_) <- .print("Vehicle Created").
+!planOrder(Id,Dimension) : vehicle(_) <-
	.print("Start PlanOrder Plan");
	-+state(waitingProposal,Id);
	+responseObtained(0,Id);
	.findall(VehicleId,vehicle(VehicleId),VS);
	.print(VS);
	.send(VS,tell,auctionOrder(Id,Dimension));
	.length(VS,C);
	+bidSent(C,Id);
	!checkWinner.

/* 
 *  Usefull to refresh the response obained value if not all arrived
 */ 
+!checkWinner[source(_)] : not all_proposals_received & responseObtained(D,I) <-
	.findall(B,proposal(A,C,I),O);
	.length(O,E);
	.findall(F,refusal(_,I),G);
	.length(G,H);
	-+responseObtained(E + H,I);
	!checkWinner.	

+!checkWinner[source(_)] : all_proposals_received <-
	-state(waitingProposal,_);
	-bidSent(_,_);
	-responseObtained(_,_);
	.findall(offer(C,A),proposal(A,C,_),O);
	.print(O);
	!checkProposal(O).

+!checkProposal(P) : P==[] <- 
	.print("here we have to spawn a new vehicle").

+!checkProposal(P) : P\==[] <- 
	.min(P,offer(Wo,Wa));
	!announce_result(P,Wa);
	.abolish(refusal(_,_));
	+state(waitingOrders).
	
+!announce_result([],_).
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
	
+proposal(_,_,_) : responseObtained(D,I) <- -+responseObtained(D+1,I).
+refusal(_,_) : responseObtained(D,I) <- -+responseObtained(D+1,I).