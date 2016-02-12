// Agent vehicle in project masSolver

/* Initial beliefs and rules */

canChallengeOrder(VehicleCapacity,OrderCapacity) :- VehicleCapacity >= OrderCapacity.
full(Capacity) :- Capacity <= 10.

vehicleCapacity(100).
currentOrder(0).
charging.
/* Initial goals */

!start.

/* Goal's Plans */

+!start : .my_name(Me) <- 
	.print("[Hello] ",Me);
	.send(planner,tell,vehicle(Me)).

//+!stop(V) : charging & ~full(V).

+!stop(V) : charging & full(V) & not pendingOrder(_,_) <-
	.print("[DeleteFromPlanner]");
	.my_name(Me); 
	-charging;
	.send(planner,tell,deleteVehicle(Me)).
	/* qua poi devi salvare i tuoi risultati */

+!testCurentCapacity(OrderID,Content) : vehicleCapacity(V) & canChallengeOrder(V,Content) <- 
	.print("[CanChallenge] ",V," [OrderContent] ",Content);
	.my_name(Me);
	/* Custom action for the computation of the Cost */
	.random(Cost);
	.send(planner, tell, proposalOrder(OrderID,Me,Cost)).
	
+!testCurentCapacity(OrderID,Content) : vehicleCapacity(V) & not canChallengeOrder(V,Content) <-
	.print("[CannotChallenge] ",V," [OrderContent] ",Content);
	.my_name(Me);
	/* Custom action for the computation of the Cost */
	.send(planner, tell, refusalOrder(OrderID,Me));
	-pendingOrder(OrderID,Content)[source(_)].

@r1 [atomic]
+!writeCapacity(OrderID) : vehicleCapacity(V) & pendingOrder(OrderID,Content) <-
	-pendingOrder(OrderID,Content)[source(planner)];
	-+vehicleCapacity(V-Content);
	+orderToServe(OrderID,Content).

@r2 [atomic]
+!restoreCapacity(OrderID) : vehicleCapacity(V) & pendingOrder(OrderID,Content) <-
    -+vehicleCapacity(V + Content);
    -pendingOrder(OrderID,Content)[source(planner)].


/* Belief's Plans */

+pendingOrder(OrderID,Content) : charging & currentOrder(I) <-
	-pendingOrder(I,_)[source(planner)];
	-+currentOrder(OrderID);
	!testCurentCapacity(OrderID,Content).
+pendingOrder(OrderID,Content) : not charging <- 
   .print("i'm dead.");
   -pendingOrder(OrderID,Content)[source(_)];
   .my_name(Me);
   .send(planner, tell, refusalOrder(OrderID,Me)).

+accept_proposal(OrderID) : vehicleCapacity(V) <- 
    .print("[AssegnedOrder] ", OrderID);
	-accept_proposal(OrderID)[source(_)];
	!writeCapacity(OrderID);
	!stop(V).

+reject_proposal(OrderID) : true <- 
	.print("[RejectedOrder]");
	-reject_proposal(OrderID)[source(planner)];
	!restoreCapacity(OrderID).
	
/*
 * DONE  --------- Fare un refactoring percui l'invio della mia proposta la faccio solamente se ho la capacita' per gestirlo, altrimenti mando un refusal (consigliato, agire sul contesto, stesso trigger event ma contesto diverso in base se ho o meno capacita'.
 * Costruire la custom action per il calcolo del costo di inserimento.
 * DONE Gestire l'accettazione e l'assegnamento dell'ordine da parte del planner: ridurre la propria capacita' e aggiungerla alla lista di ordini da soddisfare.
 * Fare un piano che, se mi ritrovo pieno, senza charging, ma con pending o capacity allora lui: cava i pending, ripristina la capacita', riaggiunge il charging e si ripresenta al planner(se non e' pieno lo stesso).
 * HALF DONE ----- Imbastire il piano che, quando la mia capacita' scende sotto una certa soglia, dice al planner di cavarlo e va a scrivere il suo risultato di pianificazione attraverso un'azione sull'environment.
 * 
 * STARE ATTENTI, FARE L'AGGIORNAMENTO DELLA CAPACITA' DEL VEICOLO IN MANIERA ATOMICA IN MODO DA EVITARE CHE IL CONTROLLO DELLA CAPACITA' AD UN NUOVO CICLO DEL PROTOCOLLO DI ASSEGNAMENTO AVVENGA PRIMA DI UNA SOTTRAZIONE DI VALORE DI CAPACITA'.
 * L'IMPORTANTE E' CHE QUESTO NON AVVENGA SE NO SON CRISTI.
 */