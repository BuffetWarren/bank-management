package main.model.account;

public class Savings extends Account{

    private static final double WITHDRAW_CHARGE = 5.0;

    public Savings(String id, String name, double balance) {
        super(id, name, balance);
    }

    public Savings(Savings source) {
        super(source);
    }

    @Override
    public boolean withdraw(double amount) {
        if(balance - amount < 0 ){
            return false;
        }
        balance = balance - amount - WITHDRAW_CHARGE;
        return true;
    }

    @Override
    public Account clone() {
        return new Savings(this);
    }
    
    
}
