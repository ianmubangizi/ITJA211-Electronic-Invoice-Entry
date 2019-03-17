/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice.Entities;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class Account {

    private String name;
    private int customer_number;
    private double balance;

    public Account() {
    }

    /*
    *
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
