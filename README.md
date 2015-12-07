# Moana -Multi Agent Approach for VRP

Thomas Farneti
Enrico Benini

## Abstract ##
The VRP falls into the category of the NP-hard problems and so it is difficult to solve it in reasonable time. It is based on interdigitation of two underlaying problems that are also NP-hard the Multiple Traveling Salesman Problem (MTSP) and Bin Packing Problem (BPP). A feasible solution to the VRP is a solution of MTSP that satisfies the capacity constraints.
The purpose of adopting a multi-agent approach is to create more opportunities for the solution to jump out of local optima, and increase the probability of reaching global optimal. Our Multi-Agents approach treats each of the customers, routes, and the global planner as an agent and allows them to exchange information and respond to the environment.  Each Agent in the system has it’s own goal and can perform a set of operations to change its status affecting the environment and modifying other agents’ status Besides operations of achieving the local objective, each agent is also responsible for satisfying constraints.
