# 🌊 Moana

> **Moana** is a Multi-Agent System for solving the **Online Vehicle Routing Problem (VRP)** in dynamic, real-time environments — built with the [Jason](http://jason.sourceforge.net/wp/) agent-oriented programming framework.

Imagine a fleet of smart, autonomous agents coordinating to deliver packages efficiently as new delivery requests arrive in real-time. That’s the mission of **Moana** — decentralized, intelligent routing at scale.

---

## 🚗 What is the Online VRP?

The **Vehicle Routing Problem (VRP)** involves finding optimal routes for a fleet of vehicles to serve a set of delivery locations.  
The **Online** variant introduces complexity: new requests arrive *during* execution, requiring agents to **adapt on the fly**.

---

## 🧠 Moana’s Approach

**Moana** adopts a **Multi-Agent System (MAS)** architecture where each vehicle is modeled as an intelligent agent. These agents:

- 🤖 Make local decisions based on real-time data
- 🧭 Collaborate to avoid route conflicts
- 🔄 Re-plan dynamically as new delivery tasks emerge

Built with **Jason**, Moana agents use **Belief-Desire-Intention (BDI)** reasoning to handle changing environments.

---

## 🧱 Architecture Overview

```
+-----------------------+
|     Dispatcher Agent  |  <-- Assigns and updates delivery tasks
+-----------------------+
          |
          v
+------------------------+     +------------------------+
|  Vehicle Agent (1..N)  | --> |   Routing Heuristics   |
+------------------------+     +------------------------+
          |
          v
+------------------------+
|   Environment Model    |
+------------------------+
```

Each component has a dedicated module:
- `core/` – Agent logic and behavior
- `messages/` – Agent communication (ACL)
- `infrastructure/` – Environment & dispatch coordination
- `persistence/` – Data logging, storage
- `test/` – Simulation scenarios and unit tests

---

## ⚙️ Getting Started

### 🔧 Prerequisites

- Java 11+
- [Jason Framework](http://jason.sourceforge.net/)
- [Gradle](https://gradle.org/) (build tool)

### 🛠 Build and Run

```bash
git clone https://github.com/thomas-farneti/Moana.git
cd Moana
./gradlew build
./gradlew run
```

You can also use IntelliJ or VS Code with Jason plugin support to run the agents in simulation mode.

---

## 📦 Features

- ✅ Fully distributed routing logic
- 🧠 BDI-based decision-making (Jason)
- 📬 Custom ACL messages between agents
- 🕹 Simulation-ready test environments
- 📈 Extensible for research on VRP, MAS, or swarm intelligence

---

## 📚 Use Cases

- Last-mile delivery in dynamic urban environments  
- Autonomous drone/package routing  
- Fleet coordination under uncertainty  
- MAS research and educational simulations

---

## 🤝 Contributing

Have an idea to improve Moana? Want to simulate a new dispatch strategy?

1. Fork this repo
2. Create a branch: `git checkout -b feature/amazing-feature`
3. Push your changes and open a PR
4. Discuss improvements or join our roadmap!
