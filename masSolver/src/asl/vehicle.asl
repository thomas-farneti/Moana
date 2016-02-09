// Agent vehicle in project masSolver

/* Initial beliefs and rules */

canChallengeOrder(VehicleCapacity,OrderCapacity) :- VehicleCapacity >= OrderCapacity.

vehicleCapacity(100).

/* Initial goals */

!start.

/* Goal's Plans */

+!start : .my_name(Me) <- 
	.print("Hello I'm ",Me);
	.send(planner,tell,vehicle(Me));
	.wait(2000);
	.send(planner,tell,order(Me,100));.

+!testCurentCapacity(OrderID,Content) : vehicleCapacity(V) & canChallengeOrder(Content,V) <- .print("I Can challenge for the order").

/* Belief's Plans */

+pendingOrder(OrderID,Content) : true <- .print("Vehicle receved pending order");
	!testCurentCapacity(OrderID,Content);
	.print("Send back my proposal");
	.my_name(Me);
	/* Custom action for the computation of the Cost */
	.random(Cost);
	.send(planner, tell, proposalOrder(OrderID,Me,Cost)).

+accept_proposal(OrderID) : true <- .print("I have to plan this shit ",OrderID ," and reduce my capacity").

+reject_proposal(OrderID) : true <- 
	.print("Planner SCREW YOU MOTHERFUCKER!!");
	-pendingOrder(OrderID,_)[source(planner)].
	
/*
 * Fare un refactoring percui l'invio della mia proposta la faccio solamente se ho la capacita' per gestirlo, altrimenti mando un refusal (consigliato, agire sul contesto, stesso trigger event ma contesto diverso in base se ho o meno capacita'.
 * Costruire la custom action per il calcolo del costo di inserimento.
 * Gestire l'accettazione e l'assegnamento dell'ordine da parte del planner: ridurre la propria capacita' e aggiungerla alla lista di ordini da soddisfare.
 * Imbastire il piano che, quando la mia capacita' scende sotto una certa soglia, dice al planner di cavarlo e va a scrivere il suo risultato di pianificazione attraverso un'azione sull'environment.
 * 
 * STARE ATTENTI, FARE L'AGGIORNAMENTO DELLA CAPACITA' DEL VEICOLO IN MANIERA ATOMICA IN MODO DA EVITARE CHE IL CONTROLLO DELLA CAPACITA' AD UN NUOVO CICLO DEL PROTOCOLLO DI ASSEGNAMENTO AVVENGA PRIMA DI UNA SOTTRAZIONE DI VALORE DI CAPACITA'.
 * L'IMPORTANTE E' CHE QUESTO NON AVVENGA SE NO SON CRISTI.
 */