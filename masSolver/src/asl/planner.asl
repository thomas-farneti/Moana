// Agent planner in project masSolver

/* Initial beliefs and rules */
all_proposals_received 
  :- .count(vehicle(_),NV) & // number of participants
     .count(proposalOrder(_,_,_), NO) &           // number of proposes received
     .count(refuse(_), NR) &              // number of refusals received
     NV = NO + NR.
     
/* Initial goals */

/* Plans */

+!planOrder(order(Content)) : true <- .print("Parte CNP");
	.findall(V,vehicle(V),VS);
	.send(VS,tell,pendingOrder(Content)).

+vehicle(Name) : true <- .print("Welcome to ", Name).

+order(Content) : .count(vehicle(_),NV) & NV >0 <- .print("Pianifico Ordine");
	!planOrder(order(Content));
	-order(Content)[source(_)].

+proposalOrder(Content,Me,Cost)[source(A)] : 
	all_proposals_received 
	<- .print("Dio caneee ",A);
	!contract.

+!contract: true
	<- .print("Guardo chi va bene");
	.findall(x(Content,Me,Cost),proposalOrder(Content,Me,Cost),L);
	.print(L);
	L \== [];
	.min(L,x(A,B,C)); // sort offers, the first is the best
  	.print("Winner so the order is ",B," with cost ",C," of the order ",A).
  	/* TODO: Inviare a tutti chi ha vinto e cancellare le proposte */
	
