// Agent planner in project masSolver

/* Initial beliefs and rules */
all_proposals_received(OrderID)
  :- .structure(proposalSended(OrderID,NV))	&			// number of participants
     .count(proposalOrder(OrderID,_,_), NO) &           // number of proposes received
     .count(refusalOrder(OrderID,_), NR) 	&			// number of refusals received
     NV = NO + NR.

listIsEmpty(L) :- L \== [].

/* Goal's Plans */
// When an order arrive is planned
+!planOrder(order(OrderID,Content)) : true <- .print("[Start CNP]");
	+order_planned(OrderID,plan);
	.findall(V,vehicle(V),VS);
	.length(VS,Challengers);
	+proposalSended(OrderID,Challengers);
	.send(VS,tell,pendingOrder(OrderID,Content)).

// The assignment for the order start.
@r1 [atomic]
+!contract(OrderID): order_planned(OrderID,plan)
	<- -order_planned(OrderID,_);
	   +order_planned(OrderID,challenge); 
	  .print("[WinnerCheckStart] Order->", OrderID);
	  .findall(offer(C,P),proposalOrder(OrderID,P,C),L);
      .print("[Offers] ",L);
      !winnerCheck(L,OrderID).
      
// previous plan sub-plan.
@r2 [atomic]
+!winnerCheck(L,OrderID) : L \== [] <- 
      .min(L,offer(WAg,WOf));
      .print("[Winner] ",WOf," [Cost] ",WAg);
      !announce_result(OrderID,L,WOf);
      -+order_planned(OrderID,finished);
      -proposalSended(OrderID,_);
      !deleteAllRefusal(OrderID).

// previous plan sub-plan.
@r3 [atomic]
+!winnerCheck(L,OrderID) : L == [] & order(OrderID,Content) <- 
    .print("[NoProposals] New Agent!");
    -+order_planned(OrderID,finished);
    -proposalSended(OrderID,_);
    !deleteAllRefusal(OrderID);
    .create_agent(B, "/bigVehicle.asl");
    .send(B,tell,pendingOrder(OrderID,Content));
    .send(B,tell,accept_proposal(OrderID)).
  	
+!announce_result(_,[],_).
// announce to the winner
+!announce_result(OrderID,[offer(_,WAg)|T],WAg) 
   <- .print("[WinnerSendMessage] Order-> ", OrderID);
   	  .send(WAg,tell,accept_proposal(OrderID));
   	  -proposalOrder(OrderID,WAg,_)[source(WAg)];
      !announce_result(OrderID,T,WAg).
// announce to others
+!announce_result(OrderID,[offer(_,LAg)|T],WAg) 
   <- .print("[WinnerSendMessage] Order-> ", OrderID);
   	  .send(LAg,tell,reject_proposal(OrderID));
   	  -proposalOrder(OrderID,LAg,_)[source(LAg)];
      !announce_result(OrderID,T,WAg).

+!deleteAllRefusal(OrderID) : not refusalOrder(OrderID,_).

+!deleteAllRefusal(OrderID) : refusalOrder(OrderID,_) <- 
    -refusalOrder(OrderID,_)[source(_)];
    !deleteAllRefusal(OrderID).

/* Belief's Plans */

// New vehicle start
+vehicle(Name) : true <- .print("[Welcome] ", Name).

// New order arrive and is planned directly if vehicles exists
+order(OrderID,Content) : .count(vehicle(_),NV) & NV >0 <- .print("[OrderArrived]");
	!planOrder(order(OrderID,Content)).

// proposals and refusals hadlers.
+proposalOrder(OrderID,Proposer,Cost): 
	order_planned(OrderID,plan) & all_proposals_received(OrderID)
	<- .print("[proposalReceived] ",OrderID);
	!contract(OrderID).

+refusalOrder(OrderID,Proposer): 
	order_planned(OrderID,plan) & all_proposals_received(OrderID)
	<- .print("[proposalReceived] ",OrderID);
	!contract(OrderID).
	
//cleanUP events
+proposalOrder(OrderID,Proposer,Cost): order_planned(OrderID,finished) <- -proposalOrder(OrderID,Proposer,Cost)[source(_)].
+refusalOrder(OrderID,Proposer): order_planned(OrderID,finished) <- -refusalOrder(OrderID,Proposer)[source(_)].
+order(OrderID,Content) : order_planned(OrderID,finished) <- -order(OrderID,_)[source(_)].
/*
 * Sistemare il fatto di andare a prendere gli ordini dall'environment e non da messaggi dei veicoli come al momento.
 * Controllare che poi tutto funzioni, soprattutto le cose dipendenti da [source()].
 * Aggiungere il piano per la cancellazione di un veicolo quando questo e' pieno e lo comunica.
 * Aggiungere il fatto che, se nessuno risponde con un'offerta viene creato un nuovo veicolo.
 */