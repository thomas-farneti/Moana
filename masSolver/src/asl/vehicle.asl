// Agent vehicle in project masSolver

/* Initial beliefs and rules */

canChallengeOrder(VehicleCapacity,OrderCapacity) :- VehicleCapacity >= OrderCapacity.

vehicleCapacity(199).

/* Initial goals */

!start.

/* Goal's Plans */

+!start : .my_name(Me) <- 
	.print("[Hello] ",Me);
	.send(planner,tell,vehicle(Me));
	.wait(2000);
	.send(planner,tell,order(Me,100)).

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
	.send(planner, tell, refusalOrder(OrderID,Me)).
	

/* Belief's Plans */

+pendingOrder(OrderID,Content) : true <- !testCurentCapacity(OrderID,Content).

+accept_proposal(OrderID) : true <- .print("[AssegnedOrder] ", OrderID).

+reject_proposal(OrderID) : true <- 
	.print("[RejectedOrder]");
	-pendingOrder(OrderID,_)[source(planner)].
	
/*
 * DONE Fare un refactoring percui l'invio della mia proposta la faccio solamente se ho la capacita' per gestirlo, altrimenti mando un refusal (consigliato, agire sul contesto, stesso trigger event ma contesto diverso in base se ho o meno capacita'.
 * Costruire la custom action per il calcolo del costo di inserimento.
 * Gestire l'accettazione e l'assegnamento dell'ordine da parte del planner: ridurre la propria capacita' e aggiungerla alla lista di ordini da soddisfare.
 * Imbastire il piano che, quando la mia capacita' scende sotto una certa soglia, dice al planner di cavarlo e va a scrivere il suo risultato di pianificazione attraverso un'azione sull'environment.
 * 
 * STARE ATTENTI, FARE L'AGGIORNAMENTO DELLA CAPACITA' DEL VEICOLO IN MANIERA ATOMICA IN MODO DA EVITARE CHE IL CONTROLLO DELLA CAPACITA' AD UN NUOVO CICLO DEL PROTOCOLLO DI ASSEGNAMENTO AVVENGA PRIMA DI UNA SOTTRAZIONE DI VALORE DI CAPACITA'.
 * L'IMPORTANTE E' CHE QUESTO NON AVVENGA SE NO SON CRISTI.
 */