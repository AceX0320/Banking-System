package twels;
import java.util.*;
public class SavingsAccount extends Account {
    private static final AccountType SAVINGS_TYPE = new AccountType("Savings", 0.025, 100.0);

    public SavingsAccount(String accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName, SAVINGS_TYPE);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && balance - amount >= SAVINGS_TYPE.getMinimumBalance()) {
            balance -= amount;
            String description = String.format("Withdrawal: -$%.2f", amount);
            transactionDescriptions.add(description);
            return true;
        }
        return false;
    }

    public double calculateInterest() {
        return balance * SAVINGS_TYPE.getInterestRate();
    }
}
