package twels;
import java.util.*;
public class Bank {
    private Map<String, Account> accounts;
    private Random random;
    private List<String> transferHistory;

    public Bank() {
        accounts = new HashMap<>();
        random = new Random();
        transferHistory = new ArrayList<>();
    }

    public String createAccount(String accountHolderName, int accountType) {
        String accountNumber = generateAccountNumber();
        Account account;

        if (accountType == 1) {
            account = new SavingsAccount(accountNumber, accountHolderName);
        } else {
            account = new CurrentAccount(accountNumber, accountHolderName);
        }

        accounts.put(accountNumber, account);
        return accountNumber;
    }

    private String generateAccountNumber() {
        return String.format("%010d", Math.abs(random.nextLong() % 10000000000L));
    }

    public void viewAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (Account account : accounts.values()) {
            System.out.printf("\nAccount Number: %s\nHolder: %s\nAccount Type: %s\nBalance: $%.2f\n",
                    account.getAccountNumber(),
                    account.getAccountHolderName(),
                    account instanceof SavingsAccount ? "Savings" : "Current",
                    account.getBalance());

            List<String> descriptions = account.getTransactionDescriptions();
            if (!descriptions.isEmpty()) {
                System.out.println("Recent Transactions:");
                for (String desc : descriptions) {
                    System.out.println("- " + desc);
                }
            }
            System.out.println("------------------------");
        }
    }

    public boolean depositToAccount(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            return true;
        }
        return false;
    }

    public boolean withdrawFromAccount(String accountNumber, double amount) {
        try {
            Account account = accounts.get(accountNumber);
            if (account != null) {
                return account.withdraw(amount);
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error withdrawing funds: " + e.getMessage());
            return false;
        }
    }

    public boolean transferFunds(String sourceAccountNumber, String destinationAccountNumber, double amount) {
        try {
            Account sourceAccount = accounts.get(sourceAccountNumber);
            Account destinationAccount = accounts.get(destinationAccountNumber);

            if (sourceAccount != null && destinationAccount != null) {
                return sourceAccount.transfer(destinationAccount, amount, "General Transfer");
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error transferring funds: " + e.getMessage());
            return false;
        }
    }

    public boolean transferFunds(String sourceAccountNumber, String destinationAccountNumber, double amount, String description) {
        try {
            Account sourceAccount = accounts.get(sourceAccountNumber);
            Account destinationAccount = accounts.get(destinationAccountNumber);

            if (sourceAccount != null && destinationAccount != null) {
                boolean result = sourceAccount.transfer(destinationAccount, amount, description);
                if (result) {
                    transferHistory.add(String.format("From: %s, To: %s, Amount: %.2f, Description: %s", sourceAccountNumber, destinationAccountNumber, amount, description));
                }
                return result;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error transferring funds: " + e.getMessage());
            return false;
        }
    }

    public void viewTransferHistory() {
        if (transferHistory.isEmpty()) {
            System.out.println("No transfer history found.");
            return;
        }
        for (String record : transferHistory) {
            System.out.println(record);
        }
    }

    public String handleStolenCard(String oldAccountNumber, String accountHolderName) {
        Account oldAccount = accounts.get(oldAccountNumber);

        if (oldAccount == null || !oldAccount.getAccountHolderName().equals(accountHolderName)) {
            return null;
        }

        String newAccountNumber = generateAccountNumber();
        Account newAccount;

        if (oldAccount instanceof SavingsAccount) {
            newAccount = new SavingsAccount(newAccountNumber, accountHolderName);
        } else {
            newAccount = new CurrentAccount(newAccountNumber, accountHolderName);
        }

        // Safely migrate full balance from old to new without violating
        // account-specific withdrawal constraints (e.g., savings min balance).
        // We directly adjust balances atomically and leave an audit entry.
        double balance = oldAccount.getBalance();
        if (balance > 0) {
            // Direct balance move within the same package (protected field access)
            oldAccount.balance = 0.0;
            newAccount.balance = balance;
            oldAccount.transactionDescriptions.add(
                String.format("Card reported stolen: moved -$%.2f to new account %s", balance, newAccountNumber)
            );
            newAccount.transactionDescriptions.add(
                String.format("Stolen card migration: +$%.2f from old account %s", balance, oldAccountNumber)
            );
        }

        accounts.put(newAccountNumber, newAccount);
        accounts.remove(oldAccountNumber);

        return newAccountNumber;
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    // Expose read-only views to support GUI rendering
    public Collection<Account> getAllAccounts() {
        return Collections.unmodifiableCollection(accounts.values());
    }

    public List<String> getTransferHistoryList() {
        return Collections.unmodifiableList(transferHistory);
    }
}