package main.model.account;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Account {
    protected String id;
    protected String name;
    protected double balance;
    protected String aa;

    public Account(String id, String name, double balance) {
        if(id == null || id.isBlank() || name == null || name.isBlank()){
            throw new IllegalArgumentException("INVALID PARAMS");
        }
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Account(Account source) {
        this.id = source.id;
        this.name = source.name;
        this.balance = source.balance;
    }


    @Override
    public String toString() {
        return (this.getClass().getSimpleName()) + "    " +
            "\t" + this.getId() + "" +
            "\t" + this.getName() + "" +
            "\t$" + this.getBalance() + "";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return round(balance);
    }
    

    public void setName(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("INVALID NAME");
        }
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(String id) {
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("INVALID ID");
        }
        this.id = id;
    }
    
    public void deposit(double amount) {
        balance += amount;      
    }
    public abstract boolean withdraw(double amount);
    public abstract Account clone();

    // protected double round(double amount) {
    //     DecimalFormat formatter = new DecimalFormat("#.##");
    //     return Double.parseDouble(formatter.format(amount));
    // }

    protected double round(double amount) {
        DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));
        return Double.parseDouble(formatter.format(amount));
    }
}
