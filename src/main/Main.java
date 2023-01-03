package main;
import main.model.Bank;
import main.model.Transaction;
import main.model.account.*;
import main.model.account.enums.TransactionType;

public class Main {

    public static void main(String[] args) {

        // Chequing chequing = new Chequing("f84c43f4-a634-4c57-a644-7602f8840870", "Michael Scott", 1524.51);
        // Savings savings = new Savings("ce07d7b3-9038-43db-83ae-77fd9c0450c9", "Saul Goodman", 2241.60);
        // Loan loan = new Loan("4991bf71-ae8f-4df9-81c1-9c79cff280a5", "Phoebe Buffay", 2537.31);

        // System.out.println(chequing);
        // System.out.println(savings);
        // System.out.println(loan);

        // Account chequingCopy = chequing.clone();

        // Account savingsCopy = savings.clone();
        Bank bank = new Bank();
        Account[] accounts = new Account[] {
            new Chequing("f84c43f4-a634-4c57-a644-7602f8840870", "Michael Scott", 1524.51),
            new Savings("ce07d7b3-9038-43db-83ae-77fd9c0450c9", "Saul Goodman", 2241.60)
        };

        for (Account account : accounts) {
            bank.addAccount(account);
        }

        Transaction[] transactions = new Transaction[] {
            new Transaction(TransactionType.WITHDRAW, 1546905600, "f84c43f4-a634-4c57-a644-7602f8840870", 624.99),
            new Transaction(TransactionType.DEPOSIT, 1578700800, "f84c43f4-a634-4c57-a644-7602f8840870", 441.93),
            new Transaction(TransactionType.WITHDRAW, 1547078400, "f84c43f4-a634-4c57-a644-7602f8840870", 546.72),
            new Transaction(TransactionType.WITHDRAW, 1546732800, "f84c43f4-a634-4c57-a644-7602f8840870", 546.72),
            new Transaction(TransactionType.DEPOSIT, 1578355200, "f84c43f4-a634-4c57-a644-7602f8840870", 635.95),
            new Transaction(TransactionType.WITHDRAW, 1547078400, "ce07d7b3-9038-43db-83ae-77fd9c0450c9", 875.64),
            new Transaction(TransactionType.WITHDRAW, 1578614400, "ce07d7b3-9038-43db-83ae-77fd9c0450c9", 912.45),
            new Transaction(TransactionType.WITHDRAW, 1577836800, "ce07d7b3-9038-43db-83ae-77fd9c0450c9", 695.09),
            new Transaction(TransactionType.WITHDRAW, 1609459200, "ce07d7b3-9038-43db-83ae-77fd9c0450c9", 917.21),
            new Transaction(TransactionType.WITHDRAW, 1578096000, "ce07d7b3-9038-43db-83ae-77fd9c0450c9", 127.94),
            new Transaction(TransactionType.WITHDRAW, 1546819200, "ce07d7b3-9038-43db-83ae-77fd9c0450c9", 612.52)
        };

        // for (Transaction transaction : transactions) {
        //     bank.addTransaction(transaction);
        // }
        
        Transaction[] filteredTransactions = bank.getTransactions("f84c43f4-a634-4c57-a644-7602f8840870");

        Account account = bank.getAccount("ce07d7b3-9038-43db-83ae-77fd9c0450c9");

    }

}
