# Banking System

A comprehensive banking system implemented in Java, offering both a **Command-Line Interface (CLI)** and a **Graphical User Interface (GUI)**. This project simulates core banking operations such as account management, fund transfers, and handling stolen cards.

---

## Features

### **Core Functionalities**
- **Account Management**: Create and manage savings or current accounts.
- **Fund Transfers**: Transfer funds between accounts with transaction descriptions.
- **Transaction History**: Log and view detailed transaction histories for all accounts.
- **Stolen Card Handling**: Securely migrate accounts when cards are reported as stolen.

### **User Interfaces**
1. **Command-Line Interface (CLI)**:
   - Menu-driven navigation.
   - Text-based interaction for all banking operations.
2. **Graphical User Interface (GUI)**:
   - Interactive GUI built with `Swing`.
   - Panels for account creation, fund management, and transaction history.

### **Additional Features**
- **Error Handling**: Validates inputs and handles errors like insufficient funds and duplicate accounts.
- **Data Persistence**: Transaction histories are maintained throughout the session.

---

## Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher.
- IDE or terminal for running Java programs.

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/AceX0320/Banking-System.git
   ```
2. Navigate to the project directory:
   ```bash
   cd Banking-System
   ```
3. Compile and run the program:
   - **For CLI**:
     ```bash
     javac src/BankingSystem.java
     java -cp src BankingSystem
     ```
   - **For GUI**:
     ```bash
     javac src/BankingSystemGUI.java
     java -cp src BankingSystemGUI
     ```

---

## Usage

### Command-Line Interface
1. Run the program and choose an option from the main menu.
2. Follow prompts to perform actions like creating accounts, transferring funds, etc.

### Graphical User Interface
1. Launch the GUI and interact with buttons and input fields for various operations.
2. Visualize transaction history and account details in tables.

---

## Project Structure

```
Banking-System/
├── src/
│   ├── Account.java          # Abstract class for bank accounts
│   ├── Bank.java             # Core banking logic
│   ├── BankingSystem.java    # Command-line interface
│   ├── BankingSystemGUI.java # Graphical user interface
│   └── ...                   # Additional supporting files
├── README.md                 # Project documentation
└── LICENSE                   # License information (to be added)
```

---

## Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch (`feature-branch`).
3. Commit your changes.
4. Push to your fork and submit a pull request.

---

## License

This project is currently not licensed. Feel free to use it for educational purposes. A license will be added soon.

---

## Contact

Developed by **[AceX0320](https://github.com/AceX0320) and [Mokh_404](https://github.com/mokh-404)**. For any inquiries or suggestions, please open an issue in the repository.
