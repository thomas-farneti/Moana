Objective [![Build Status](https://travis-ci.org/thomas-farneti/Moana.svg?branch=master)](https://travis-ci.org/thomas-farneti/Moana)
===============

With this project we want to provide a solution at the Vehicle Rooting Problem through a Multi-Agent System approach using the Jason platform.

Structure
-------------

We decide to split the project in some sub-project due to the maintenance of the code and for the separation of concerns. In detail:

* *Core*: A base infrastructure that provide Utilities as middleware for all the actors in the system.
* *messages*: This project define the representation of the messages in the MAS in order to achieve the exchange of information.
* *persistence*: This layer provide storage functionality for the resume of the system.
* *test*: There's the Unit test and integration test for the project.
