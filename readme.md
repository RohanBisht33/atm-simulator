# ATM Simulator & Database-Backed Concurrent Transaction Engine

An enterprise-grade, high-concurrency banking application built natively on Linux using core Java and PostgreSQL. This project showcases structural engineering paradigms including clean architectural layering, defensive exception boundaries, thread isolation parameters, and a pre-compiled relational data persistence engine.

## 📦 Core Operational Domain
The application bootstraps a centralized command-line interface (CLI) to process real-time financial workflows over a persistent database matrix:
* **Relational Authentication:** Registers banking accounts and opens active, state-validated security sessions.
* **ACID Transactions:** Executes thread-safe currency updates (`Withdraw`, `Deposit`) with precise mathematical evaluation.
* **Deterministic Guardrails:** Employs immediate business-logic validation to intercept account overdrafts, adversarial inputs, and negative balances.

---

## 🏗️ Production-Grade Architectural Pillars

### 1. Persistent RDBMS Persistence Layer (The DAO Pattern)
This project has transitioned completely away from loose, transient file tracking and temporary file serialization. It implements a robust **Data Access Object (DAO)** pattern managed by `UserDAO.java`.
* **Normalized Database Schema:** Isolates raw credentials indexing (`users`) from operational finance ledger records (`accounts`) using relational foreign key bounds with explicit `ON DELETE CASCADE` constraints.
* **SQL Injection Neutralization:** Queries utilize pre-compiled `PreparedStatement` boundaries. Every user parameter maps to strict value types (`?` placeholders), forcing the database engine to treat incoming metrics purely as literal strings rather than executable logic instructions.
* **Resource Leak Protection:** Network sockets and server descriptors are wrapped inside defensive `try-with-resources` blocks. This ensures the Java Virtual Machine automatically triggers fundamental `.close()` sweeps to recycle active operating system file slots, even under sudden execution thread failures.

### 2. High-Concurrency Concurrency Controls (Lock Contention Tuning)
To protect transaction fields under multi-threaded client execution, state consistency is managed through explicit thread synchronization bounds:
* **Critical Section Minimization:** Traditional thread blocking is bypassed by pulling slow blocking console I/O streams (`input.next()`) completely **outside** the synchronized critical window.
* **Optimized Lock Acquisition:** Threads only acquire an account's `ReentrantLock` for the absolute split-second needed to execute binary state mutations and commit the fresh numerical totals straight to the persistent database on disk, dropping lock contention overhead to near zero.

### 3. State Isolation & Security Architecture
* **Strict Encapsulation Bounds:** Core entity properties (e.g., matching IDs, encrypted passkeys, and financial variables) are strictly protected inside private data fields accessible only via tightly regulated domain methods.
* **Isolated Verification Paths:** To prevent dangerous object corruption traps, validation routines utilize state-isolation mechanics. User verification variables are processed in temporary instance spaces, preventing failed user verification loops from corrupting the active system memory state into an illegal null pointer.

### 4. Decoupled Command Pattern Map Dispatching
Traditional, unmanageable code designs (such as long `switch-case` constructs or nested `if-else` loops) are completely avoided. 
* Menu routes are isolated into standalone, stateless **Command Objects** implementing a common `ATMCommand` contract interface routing structure.
* Navigation choices map straight into a centralized menu repository, enabling constant-time **$O(1)$ dispatch routing** and providing an architecture that is endlessly extensible when adding new domain operations.

### 5. Custom Exception Circuit-Breaker Tier
Rather than relying on generic runtime alerts, the application applies highly targeted defensive validation bounds by extending `RuntimeException` (e.g., `InsufficientFundsException`, `NegativeFundsException`, `MinimumPasswordLengthException`). These custom exceptions function as immediate runtime **circuit breakers** that gracefully halt processing upon business-rule violations, preserving data integrity without crashing the master workspace thread.

---

## 📁 Repository Directory Layout

The codebase strictly enforces industry-standard software layer boundaries to completely decouple business logic from the persistence implementation:

```text
├── pom.xml                     # Maven project descriptor & dependency configuration
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── bank
    │               ├── command        # Command Pattern interface modules (Login, Create, Withdraw, Deposit)
    │               ├── exception      # Custom business-rule validation circuit breakers
    │               ├── persistence    # DatabaseManager connection engine & UserDAO persistence layer
    │               ├── security       # SecurityService password hash processing layers
    │               ├── Account.java   # Strictly encapsulated, thread-locked domain entity model
    │               └── Main.java      # Framework master bootstrap execution loop
    └── test
        └── java
            └── com
                └── bank
                    └── test           # JUnit automated verification validation suites
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
mvn exec:java -Dexec.mainClass="com.bank.Main"
```
