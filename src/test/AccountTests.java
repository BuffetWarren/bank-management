package test;

import main.model.account.*;
//import main.model.account.interfaces.Taxable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTests {
    private static final double CHEQUING_INITIAL_BALANCE = 1524.51;
    private static final double SAVINGS_INITIAL_BALANCE = 2241.60;
    private static final double LOAN_INITIAL_BALANCE = 2537.31;

    Account chequing, savings, loan;
    // private final double CHEQUING_INITIAL_BALANCE =

    @Before
    public void setup() {
        chequing = new Chequing("f84c43f4-a634-4c57-a644-7602f8840870", "Michael Scott", CHEQUING_INITIAL_BALANCE);
        savings = new Savings("ce07d7b3-9038-43db-83ae-77fd9c0450c9", "Saul Goodman", SAVINGS_INITIAL_BALANCE);
        loan = new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5", "Phoebe Buffay", LOAN_INITIAL_BALANCE);
    }

    @Test
    public void withdrawal() {
        chequing.withdraw(1440);
        assertEquals(84.51, chequing.getBalance());
    }

    @Test
    public void overdraft() {
        chequing.withdraw(1534.43);
        assertEquals(-15.42, chequing.getBalance());
    }

    @Test
    public void overdraftLimit() {
        chequing.withdraw(1724.51);
        assertEquals(CHEQUING_INITIAL_BALANCE, chequing.getBalance());
    }

    @Test
    public void withdrawalFee() {
        savings.withdraw(100);
        assertEquals(2136.60, savings.getBalance());
    }

    @Test
    public void withdrawalInterest() {
        loan.withdraw(2434.31);
        assertEquals(5020.31, loan.getBalance());
    }

    @Test
    public void withdrawalLimit() {
        loan.withdraw(7463.69);
        assertEquals(LOAN_INITIAL_BALANCE, loan.getBalance());
    }

    @Test
    public void deposit() {
        chequing.deposit(5000);
        assertEquals(6524.51, chequing.getBalance());
    }

    @Test
    public void loanDeposit() {
        loan.deposit(1000);
        assertEquals(1537.31, loan.getBalance());
    }

    @Test
    public void incomeTax() {
        double income = 4000;
        chequing.deposit(income);

        ((Chequing) chequing).tax(income);
        assertEquals(5374.51, chequing.getBalance());
    }

}