package com.electronic_invoice.Entities;

/**
 * Account Entity Class
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class Account {

    private String name;
    private int customer_number;
    private double balance;

    public Account() {
    }

    /**
     * Account Constructor
     *
     * @param name The name of the Customer
     * @param customer_number The Customer's number
     * @param balance The Account balance
     */
    public Account(String name, int customer_number, double balance) {
        this.name = name;
        this.customer_number = customer_number;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(int customer_number) {
        this.customer_number = customer_number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
