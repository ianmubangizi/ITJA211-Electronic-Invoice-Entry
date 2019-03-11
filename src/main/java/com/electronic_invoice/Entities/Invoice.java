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

public class Invoice {
    private int invoice_number;
    private int customer_number;
    private double payment;

    public Invoice() {
    }

    public Invoice(String invoice_number, String customer_number, String payment) {
        this.invoice_number = Integer.parseInt(invoice_number);
        this.customer_number = Integer.parseInt(customer_number);
        this.payment = double.class.cast(payment);
    }

    public int getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(int invoice_number) {
        this.invoice_number = invoice_number;
    }

    public int getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(int customer_number) {
        this.customer_number = customer_number;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
    
}
