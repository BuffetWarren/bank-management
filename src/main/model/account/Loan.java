package main.model.account;

public class Loan extends Account {

    private static final double INTEREST_RATE = 0.02;
    private static final double MAX_DBT = 10000.0;

    public Loan(String id, String name, double balance) {
        super(id, name, balance);
    }

    public Loan(Loan source){
        super(source);
    }

    @Override
    public void deposit(double amount) {
       balance -= amount;
        
    }

    @Override
    public boolean withdraw(double amount) {
        if(balance + amount > MAX_DBT){
            return false;
        }
        balance = balance + amount +(amount * INTEREST_RATE);
        return true;
    }

    @Override
    public Account clone() {
        return new Loan(this);
    }
    
}
