// Agent planner in project masSolver

/* Initial beliefs and rules */
all_proposals_received(OrderID)
  :- .count(vehicle(_),NV) 					&			// number of participants
     .count(proposalOrder(OrderID,_,_), NO) &           // number of proposes received
     .count(refuse(_), NR) 					&			// number of refusals received
     NV = NO + NR.
     
/* Initial goals */

/* Plans */

+!planOrder(order(OrderID,Content)) : true <- .print("Parte CNP");
	+order_planned(OrderID,plan);
	.findall(V,vehicle(V),VS);
	.send(VS,tell,pendingOrder(OrderID,Content)).

+vehicle(Name) : true <- .print("Welcome to ", Name).

+order(OrderID,Content) : .count(vehicle(_),NV) & NV >0 <- .print("Pianifico Ordine");
	!planOrder(order(OrderID,Content));
	-order(OrderID,Content).

+proposalOrder(OrderID,Proposer,Cost): 
	order_planned(OrderID,plan) & all_proposals_received(OrderID)
	<- .print("All proposal received ",OrderID);
	!contract(OrderID).

+refusalOrder(OrderID,Proposer): 
	order_planned(OrderID,plan) & all_proposals_received(OrderID)
	<- .print("All proposal received ",OrderID);
	!contract(OrderID).

@r1 [atomic]
+!contract(OrderID): order_planned(OrderID,plan)
	<- -order_planned(OrderID,_);
	   +order_planned(OrderID,challenge); 
	  .print("Check for winner of the order ", OrderID);
	  .findall(offer(C,P),proposalOrder(OrderID,P,C),L);
      .print("Offers are ",L);
      L \== []; 
      .min(L,offer(WAg,WOf));
      .print("Winner is ",WOf," with ",WAg);
      !announce_result(OrderID,L,WOf);
      -+order_planned(OrderID,finished).
  	  /* TODO: Inviare a tutti chi ha vinto e cancellare le proposte */
  	
+!announce_result(_,[],_).
// announce to the winner
+!announce_result(OrderID,[offer(_,WAg)|T],WAg) 
   <- .print("Send to the winner of ", OrderID);
   	  .send(WAg,tell,accept_proposal(OrderID));
   	  .print("Delete the proposal of", WAg);
   	  -proposalOrder(OrderID,WAg,_)[source(WAg)];
      !announce_result(OrderID,T,WAg).
// announce to others
+!announce_result(OrderID,[offer(_,LAg)|T],WAg) 
   <- .print("Send to the winner of ", OrderID);
   	  .send(LAg,tell,reject_proposal(OrderID));
   	  .print("Delete the proposal of", LAg);
   	  -proposalOrder(OrderID,LAg,_)[source(LAg)];
      !announce_result(OrderID,T,WAg).