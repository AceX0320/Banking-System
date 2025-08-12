package twels;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
public class BankingSystemTest {
    @Test
    public void testSavingsAccountWithdrawal() {
        SavingsAccount account = new SavingsAccount("1234", "John Doe");
        account.deposit(200.0);
        boolean result = account.withdraw(50.0);
        assertTrue(result);
        assertEquals(150.0, account.getBalance(), 0.01);
    }
}