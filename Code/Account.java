package twels;
import java.util.*;
public abstract class Account {
    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;
    protected List<String> transactionDescriptions;
    protected AccountType accountType;

    public Account(String accountNumber, String accountHolderName, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
        this.transactionDescriptions = new ArrayList<>();
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public abstract boolean withdraw(double amount);

    public boolean transfer(Account destinationAccount, double amount, String description) {
        if (amount > 0 && this.withdraw(amount)) {
            destinationAccount.deposit(amount);
            destinationAccount.transactionDescriptions.add(description);
            return true;
        }
        return false;
    }

    public List<String> getTransactionDescriptions() {
        return transactionDescriptions;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}