package main.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import main.model.account.Account;
import main.model.account.Chequing;
import main.model.account.interfaces.Taxable;

public class Bank {
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;

    public Bank() {
        this.accounts = new ArrayList<Account>();
        this.transactions = new ArrayList<Transaction>();
    }

    public void addAccount(Account account) {
        this.accounts.add(account.clone());
    }

    private void addTransaction(Transaction transaction) {
        this.transactions.add(new Transaction(transaction));
    }

    private void withdrawTransaction(Transaction transaction) {
        if (getAccount(transaction.getId()).withdraw(transaction.getAmount())) {
            addTransaction(transaction);
        }
    }

    private void depositTransaction(Transaction transaction) {
        getAccount(transaction.getId()).deposit(transaction.getAmount());
        addTransaction(transaction);
    }

    public void executeTransaction(Transaction transaction) {
        switch(transaction.getType()){
            case DEPOSIT: depositTransaction(transaction); break;
            case WITHDRAW: withdrawTransaction(transaction); break;
        }
    }

    public Transaction[] getTransactions(String transactionId) {
        List<Transaction> list = this.transactions.stream()
                .filter((transaction) -> transaction.getId().equals(transactionId))
                .collect(Collectors.toList());

        return list.toArray(new Transaction[list.size()]);
    }

    public Account getAccount(String accountId) {
        return accounts.stream()
                .filter((account) -> account.getId().equals(accountId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Name: getIncome
     * @param account (Taxable)
     * @return double
     * 
     * Inside the function:
     *   1. Gets every transaction that matches the account's id.
     *   2. Maps every transaction to a double.
     *       - Transactions of type WITHDRAW are mapped to negative numbers.
     *       - Transactions of type DEPOSIT are mapped to positive numbers.
     *   3. Takes the sum of every number and returns the income.
     * 
     */
    public double getIncome(Taxable account){
        Transaction[] transactions = getTransactions(((Chequing)account).getId());

        double sum = Arrays.stream(transactions).mapToDouble((transaction)->{
            switch(transaction.getType()){
                case DEPOSIT: return transaction.getAmount();
                case WITHDRAW: return -transaction.getAmount();
                default: return 0;
            }
        }).sum();
        
        return sum;
    }

    public void deductTaxes() {
        for (Account account : accounts) {
            if (Taxable.class.isAssignableFrom(account.getClass())) {
                Taxable taxable = (Taxable)account;
                taxable.tax(getIncome(taxable));
            }
        }
    }
    

}
