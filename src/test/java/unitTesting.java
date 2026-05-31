import com.bank.Account;
import com.bank.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class unitTesting {

    @Test
    public void testValidWithdrawal() {
        Account account = new Account("Alice", "123",10000);
        account.Withdraw(3000);
        // Assertions check if the actual value matches your exact expectation
        assertEquals(7000, account.showBalance());
    }

    @Test
    public void testInsufficientFundsThrowsException() {
        Account account = new Account("Bob", "234",2000);

        // This asset structure verifies that the engine explicitly throws the right error
        assertThrows(InsufficientFundsException.class, () -> {
            account.Withdraw(5000);
        });
    }
    @Test
    public void testMaximumLimitReachedThrowsException() {
        Account account = new Account("Jon", "345",100000);

        // This asset structure verifies that the engine explicitly throws the right error
        assertThrows(MaximumLimitReachedException.class, () -> {
            account.Withdraw(60000);
        });
//        assertThrows(MaximumLimitReachedException.class, () -> {
//            account.Deposit(80000);
//        });
    }
    @Test
    public void testNegativeFundsThrowsException() {
        Account account = new Account("Jane", "456",2000);

        // This asset structure verifies that the engine explicitly throws the right error
        assertThrows(NegativeFundsException.class, () -> {
            account.Deposit(-5000);
        });
    }
}