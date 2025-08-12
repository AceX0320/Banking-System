package twels;
import java.util.*;
public class BankingSystem {
    private final Bank bank;
    private static Scanner scanner = new Scanner(System.in);

    public BankingSystem() {
        this.bank = new Bank();
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        bankingSystem.start();
    }

    public void start() {
        System.out.println("Welcome to the Banking System");

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    bank.viewAccounts();
                    break;
                case 3:
                    depositFunds();
                    break;
                case 4:
                    withdrawFunds();
                    break;
                case 5:
                    transferFunds();
                    break;
                case 6:
                    handleStolenCard();
                    break;
                case 7:
                    bank.viewTransferHistory();
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n1. Create Account");
        System.out.println("2. View Accounts");
        System.out.println("3. Deposit Funds");
        System.out.println("4. Withdraw Funds");
        System.out.println("5. Transfer Funds");
        System.out.println("6. Report Stolen Card");
        System.out.println("7. View Transfer History");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private void createAccount() {
        System.out.println("\nCreating Account:");
        System.out.println("Choose account type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");

        String name;
        while (true) {
            System.out.print("Enter account holder name: ");
            name = scanner.nextLine().trim();
            if (name.matches("^[a-zA-Z\\s]+$") && !name.isEmpty()) {
                break;
            }
            System.out.println("Invalid name! Please use only letters and spaces.");
        }

        System.out.print("Enter account type (1/2): ");
        int type = scanner.nextInt();

        if (type != 1 && type != 2) {
            System.out.println("Invalid account type!");
            return;
        }

        String accountNumber = bank.createAccount(name, type);
        System.out.println("Account created successfully! Account number: " + accountNumber);
    }

    private void depositFunds() {
        System.out.println("\nDepositing Funds:");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        if (bank.getAccount(accountNumber) == null) {
            System.out.println("Account not found! Please check the account number.");
            return;
        }

        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        if (bank.depositToAccount(accountNumber, amount)) {
            System.out.printf("Funds deposited successfully! Current balance: $%.2f%n",
                    bank.getAccount(accountNumber).getBalance());
        } else {
            System.out.println("Failed to deposit funds. Amount must be positive.");
        }
    }

    private void withdrawFunds() {
        System.out.println("\nWithdrawing Funds:");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        if (bank.getAccount(accountNumber) == null) {
            System.out.println("Account not found! Please check the account number.");
            return;
        }

        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (bank.withdrawFromAccount(accountNumber, amount)) {
            System.out.printf("Funds withdrawn successfully! Current balance: $%.2f%n",
                    bank.getAccount(accountNumber).getBalance());
        } else {
            Account account = bank.getAccount(accountNumber);
            if (account instanceof SavingsAccount) {
                System.out.println("Failed to withdraw funds. Insufficient balance or below minimum balance requirement.");
            } else {
                System.out.println("Failed to withdraw funds. Insufficient balance or exceeds overdraft limit.");
            }
        }
    }

    private void transferFunds() {
        System.out.println("\nTransferring Funds:");
        System.out.print("Enter source account number: ");
        String sourceAccount = scanner.nextLine();

        // Verify source account exists
        if (bank.getAccount(sourceAccount) == null) {
            System.out.println("Source account not found!");
            return;
        }

        System.out.print("Enter destination account number: ");
        String destAccount = scanner.nextLine();

        if (bank.getAccount(destAccount) == null) {
            System.out.println("Destination account not found!");
            return;
        }


        if (sourceAccount.equals(destAccount)) {
            System.out.println("Cannot transfer to the same account!");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter transfer description (e.g., rent, food): ");
        String description = scanner.nextLine();

        if (bank.transferFunds(sourceAccount, destAccount, amount, description)) {
            System.out.printf("Funds transferred successfully! Current balance in source account: $%.2f%n",
                    bank.getAccount(sourceAccount).getBalance());
        } else {
            System.out.println("Failed to transfer funds. Please check account balance.");
        }
    }

    private void handleStolenCard() {
        System.out.println("\nReport Stolen Card:");
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        String newAccountNumber = bank.handleStolenCard(accountNumber, name);
        if (newAccountNumber != null) {
            System.out.println("Account successfully secured!");
            System.out.println("Your new account number is: " + newAccountNumber);
            System.out.println("All funds have been transferred to the new account.");
        } else {
            System.out.println("Failed to process request. Please verify your account details.");
        }
    }
}