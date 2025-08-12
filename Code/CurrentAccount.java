package twels;
import java.util.*;
public class CurrentAccount extends Account {
    private static final AccountType CURRENT_TYPE = new AccountType("Current", 0.0, 0.0);

    public CurrentAccount(String accountNumber, String accountHolderName) {
        super(accountNumber, accountHolderName, CURRENT_TYPE);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            String description = String.format("Withdrawal: -$%.2f", amount);
            transactionDescriptions.add(description);
            return true;
        }
        return false;
    }
}