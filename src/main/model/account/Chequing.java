package main.model.account;

import main.model.account.interfaces.Taxable;

public class Chequing extends Account implements Taxable{

    private static final double OVERDRAFT_FEE = 5.50;
    private static final double OVERDRAFT_LIMIT = -200;
    private static final double TAX_RATE = 0.15;
    private static final double IMCOME_TAXE = 3000;

    public Chequing(String id, String name, double balance) {
        super(id, name, balance);
    }

    public Chequing(Chequing source) {
        super(source);
    }

    @Override
    public boolean withdraw(double amount) {
        if(balance - amount < 0){
            if(balance - amount <= OVERDRAFT_LIMIT){
                return false;
            }
            balance = balance - amount - OVERDRAFT_FEE;
            return true;
        }
        balance -= amount;
        return true;
    }

    @Override
    public void tax(double income) {
        double tax = (income - IMCOME_TAXE)*TAX_RATE; 
        balance -= tax;
        
    }

    @Override
    public Account clone() {
        return new Chequing(this);
    }

       
}
