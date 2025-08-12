package twels;
import java.util.*;
public class AccountType {
    private String typeName;
    private double interestRate;
    private double minimumBalance;

    public AccountType(String typeName, double interestRate, double minimumBalance) {
        this.typeName = typeName;
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
    }

    public String getTypeName() {
        return typeName;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }
}
