package main.model;

import java.text.SimpleDateFormat;
import java.util.*;

import main.model.account.enums.TransactionType;

public class Transaction implements Comparable<Transaction>{

    private TransactionType type;
    private long timestamp;
    private String id;
    private double amount;
    


    public Transaction(TransactionType type, long timestamp, String id, double amount) {
        this.type = type;
        this.timestamp = timestamp;
        this.id = id;
        this.amount = amount;
    }

    public Transaction(Transaction source){
        this.type = source.type;
        this.timestamp = source.timestamp;
        this.id = source.id;
        this.amount = source.amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("INVALID ID");
        }
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if(amount < 0){
            throw new IllegalArgumentException("THE AMOUNT CANNOT BE NEGATIVE");
        }
        this.amount = amount;
    }

    public String returnDate(){
        Date date = new Date(this.timestamp * 1000);
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    @Override
    public int compareTo(Transaction o) {

        return Double.compare(this.timestamp, o.timestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Transaction)) {
            return false;
        }
        Transaction transaction = (Transaction) o;
        return Objects.equals(type, transaction.type) && timestamp == transaction.timestamp && Objects.equals(id, transaction.id) && amount == transaction.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, timestamp, id, amount);
    }

    @Override
    public String toString() {
        return (type) + "    " +
            "\t" + this.returnDate()+ "" +
            "\t" + this.id + "" +
            "\t$" + this.amount + "";
    }

    
    
}
