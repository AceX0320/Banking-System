# Java (OOP) Banking System

A simple banking system demonstrating OOP in Java with two UIs:
- CLI app: `twels.BankingSystem`
- GUI app (Swing): `twels.BankingSystemGUI`

## Requirements
- Java JDK 17+ (tested with JDK 21)
- Windows PowerShell or any shell

## Project Layout
- `Code/` — Java sources in package `twels`
  - `BankingSystem.java` — CLI entry point
  - `BankingSystemGUI.java` — Swing GUI entry point
  - `Bank.java`, `Account.java`, `SavingsAccount.java`, `CurrentAccount.java`, `AccountType.java`
- `out/` — Compiled classes (created by the commands below)

Important: Edit source files only in `Code/`. Do not edit `out/`; it is generated during build and can be safely deleted and recreated by recompiling.

## Build
From the project root:

```powershell
# Compile all sources to the out/ directory
javac -encoding UTF-8 -d out "Code\*.java"
```

## Run (GUI)
Launch the Swing GUI application:

```powershell
java -cp out twels.BankingSystemGUI
```

## Run (CLI)
Launch the console menu-driven application:

```powershell
java -cp out twels.BankingSystem
```

## Notes
- The GUI provides tabs for Accounts, Deposit, Withdraw, Transfer (with description), Stolen Card handling, and global Transfer History.
- Account numbers are generated automatically. Names must contain only letters and spaces.
- Savings accounts enforce a minimum balance; current accounts do not allow overdraft.

## Features
- Create Savings or Current accounts
- Deposit and withdraw funds with validation
- Transfer between accounts with descriptions and global history
- Handle stolen cards with safe balance migration
- View recent transactions per account

## Architecture
- Domain layer in `twels` package:
  - `Account` (abstract), `SavingsAccount`, `CurrentAccount`, `AccountType`
  - `Bank` service manages accounts, transfers, and history
- Presentation:
  - CLI: `BankingSystem` (console menu)
  - GUI: `BankingSystemGUI` (Swing, tabbed interface)

## Project Structure
```
Code/
  Account.java
  AccountType.java
  Bank.java
  BankingSystem.java        # CLI entry
  BankingSystemGUI.java     # GUI entry
  CurrentAccount.java
  SavingsAccount.java
out/                        # Compiled classes (after build)
```

## Troubleshooting
- Ensure a recent JDK is installed and `java`/`javac` are on PATH.
- If the app cannot find classes, recompile with the Build command above and run with `-cp out`.
- For non-Windows shells, adjust quoting of the glob pattern if needed.

## License
This project is provided as-is for educational purposes. Add a license here if you plan to distribute it.

# Banking System

![Banking System](https://img.shields.io/badge/Banking-System-blue)
![License](https://img.shields.io/github/license/AceXS0320/Banking-System)
![Version](https://img.shields.io/badge/version-1.0.0-green)

## Overview

This Banking System is a comprehensive software solution designed to manage banking operations efficiently. It provides a secure and user-friendly platform for account management, transactions, and financial operations.

## Features

- **Account Management**
  - Create, update, and close customer accounts
  - Support for different account types (Savings, Checking, Fixed Deposit)
  - Account balance inquiry and statement generation

- **Transaction Processing**
  - Deposits and withdrawals
  - Fund transfers between accounts
  - Scheduled payments and recurring transactions

- **Security Features**
  - Two-factor authentication
  - Role-based access control
  - Encrypted data storage and transmission
  - Transaction monitoring and fraud detection

- **Reporting & Analytics**
  - Financial reports generation
  - Transaction history and audit trails
  - Customer activity insights

## Technology Stack

- **Backend**: [Specify languages/frameworks used]
- **Database**: [Specify database system]
- **Frontend**: [Specify if applicable]
- **Security**: [Encryption methods, authentication systems]

## Installation

```bash
# Clone the repository
git clone https://github.com/AceXS0320/Banking-System.git

# Navigate to the project directory
cd Banking-System

# Install dependencies
# [Add specific installation commands based on your tech stack]

# Configure the environment
# [Add configuration steps]

# Run the application
# [Add run commands]
```

## Project Structure

```
Banking-System/
├── src/                # Source code
├── config/             # Configuration files
├── docs/               # Documentation
├── tests/              # Test files
└── README.md           # This file
```

## Usage

### Account Management

```python
# Example code for creating a new account
# [Add specific code examples]
```

### Processing Transactions

```python
# Example code for processing a transaction
# [Add specific code examples]
```

## Testing

```bash
# Run tests
# [Add test commands]
```

## Roadmap

- [ ] Mobile application integration
- [ ] Enhanced reporting features
- [ ] International transfer capabilities
- [ ] Investment account management


## Contact

Team of people

Project Link: [https://github.com/AceXS0320/Banking-System](https://github.com/AceXS0320/Banking-System)

