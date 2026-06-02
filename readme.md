# ATM Simulator & Modular Backend Transaction Engine

An interactive, production-grade terminal banking application built from scratch in Java. This project goes beyond basic scripting mechanics to showcase strict object encapsulation, advanced design patterns, defensive exception boundaries, and automated testing lifecycles.

## 📦 What does this project do?
This repository contains a centralized execution engine that runs an interactive **ATM Simulator Module** through a local terminal interface. 

### Core Features:
* **User Authentication:** Systematically registers unique profiles and opens active, state-protected banking sessions.
* **Atomic Transactions:** Processes real-time financial updates including balance checks, cash deposits, and withdrawals.
* **Systemic Guardrails:** Deploys immediate error validation to block overdrafts, negative inputs, and numeric overflows.

## 🏗️ Architectural Key Highlights
* **The Command Pattern Map:** Completely eradicates traditional, hardcoded `switch-case` loops. Every terminal action is isolated into a stateless Command class and registered in an in-memory `HashMap` for fast $O(1)$ dispatching.
* **Custom Runtime Exceptions:** Utilizes a targeted exception tier extending `RuntimeException` to act as an immediate business-logic circuit breaker, protecting data state integrity without crashing the application thread.

## 🏗️ Architectural Core Pillars

### 1. Unified Workspace & Pluggable Domains
Instead of building a standalone, isolated ATM application, this project uses a multi-module layout. The core framework handles the bootstrapping, while the ATM logic lives as a pluggable package. This means a developer can easily plug an entirely new application (like a Terminal Inventory Manager or a Library Catalog) directly into the framework without modifying any of the core codebase.

### 2. Eradicating Switch-Cases (The Command Pattern Map)
In standard terminal applications, user input is typically passed through massive, hardcoded `switch` blocks or nested `if-else` loops. This project completely eliminates those loops to comply with clean coding standards:
* Every user action (e.g., `Withdraw`, `Deposit`, `Logout`) is decoupled into its own independent, stateless **Command Class**.
* All available commands are stored inside an in-memory **Command Registry Map**.
* When a user types a command, the system retrieves it instantly with an optimized **$O(1)$ constant-time lookup**, making the terminal menu endlessly extensible when adding new operations.

### 3. Strict Object Encapsulation & Memory Guardrails
Data mutation paths are governed by rigid object isolation principles. State properties (e.g., identity metrics, ledger inputs, financial balances) are enforced with `private` visibility constraints. Memory pointers and reference leaks are strictly mitigated, ensuring that data states can only be altered via verified, atomic domain operations.

### 4. Self-Documenting Custom Exception Tier
To prevent data corruption, the system implements an explicit defensive validation boundary. By extending `RuntimeException`, the workspace establishes a custom, self-documenting domain exception tier. These custom blocks function as instantaneous runtime **circuit breakers** that intercept adversarial parameters (e.g., numerical overflows, negative limits, state violations) and gracefully return control to the interface layer without crashing the execution thread.

### 5. Automated Build & Verification Lifecycle
Code stability is programmatically enforced using **JUnit 5** test suites executed directly via the **Maven build engine**. The testing layer maps out comprehensive path coverage, exercising boundary values and exception propagation parameters to validate system behavior under simulated architectural failure modes.

---

## 📁 Repository Directory Layout

The workspace strictly enforces standard enterprise layout conventions for clean separation of application logic and automated test suites:

```text
├── pom.xml                        # Master Maven configuration & dependency tree
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── bank
    │                ├── command   # Command pattern & registry architecture
    │                ├── persistence     # Encapsulated state object models
    │                ├── security     # Encapsulated state object models
    │                ├── exception # Custom runtime circuit breaker tier
    │                └── Main.java  # Workspace bootstrap & execution loop
    └── test
        └── java
            └── com
                └── bank
                    └── test           # JUnit 5 automated test verification suites
```
****🛠️ Compilation & Local Execution****
This workspace is designed to be managed natively within a clean terminal environment (such as a Linux terminal layout) utilizing raw developer utilities without relying on IDE automation crutches.

**1. Compile and Validate the Workspace**
To clean temporary compilation metrics, compile the underlying source code, and run the automated JUnit 5 test suite, execute:

Bash
```
mvn clean test
```
**2. Launch the Centralized Runtime Engine**
To bootstrap the primary application workspace framework and open the interactive console execution channel:

Bash
```
mvn exec:java -Dexec.mainClass="com.framework.core.Main"
```
