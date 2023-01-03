package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import main.model.Bank;
import main.model.Transaction;
import main.model.account.Chequing;
import main.model.account.enums.TransactionType;

public class BankTests {
    Bank bank;

    @Before
    public void setup() {
        bank = new Bank();
        bank.addAccount(new Chequing("f84c43f4-a634-4c57-a644-7602f8840870", "Michael Scott", 1524.51));
    }

    @Test
    public void bankAddSuccessfullTransaction() {
        this.bank.executeTransaction(
                new Transaction(TransactionType.WITHDRAW, 1546905600, "f84c43f4-a634-4c57-a644-7602f8840870", 624.99));
        this.bank.executeTransaction(
                new Transaction(TransactionType.DEPOSIT, 1578700800, "f84c43f4-a634-4c57-a644-7602f8840870", 441.93));
        assertEquals(2, bank.getTransactions("f84c43f4-a634-4c57-a644-7602f8840870").length);
    }

    @Test
    public void bankIgnoreFailedTransaction() {
        this.bank.executeTransaction(new Transaction(TransactionType.WITHDRAW, 1546905600,
                "f84c43f4-a634-4c57-a644-7602f8840870", 10000000));
        assertEquals(0, bank.getTransactions("f84c43f4-a634-4c57-a644-7602f8840870").length);
    }

    @Test
    public void taxDeduction() {
        this.bank.executeTransaction(
                new Transaction(TransactionType.DEPOSIT, 1578700800, "f84c43f4-a634-4c57-a644-7602f8840870", 4000));
        this.bank.deductTaxes();
        assertEquals(5374.51, bank.getAccount("f84c43f4-a634-4c57-a644-7602f8840870").getBalance(), 10);
    }

}
