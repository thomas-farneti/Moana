// Agent testAgent in project masSolver

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.");
test.

+order(Id) : true <- .print("Recived ",Id).
