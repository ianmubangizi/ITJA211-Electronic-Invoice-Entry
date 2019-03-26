package com.electronic_invoice.Entities;

/**
 * Invoice Entity Class
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */

public class Invoice {
    private int invoice_number;
    private int customer_number;
    private double payment;

    public Invoice() {
    }

    /**
     * Invoice Constructor
     * 
     * @param invoice_number  The Invoice number <strong>Note:</strong> This must be
     *                        unique per instance
     * @param customer_number Similar to the
     *                        <code>Customer(int customer_number, ...){}</code>
     * @param payment
     */
    public Invoice(int invoice_number, int customer_number, double payment) {
        this.invoice_number = invoice_number;
        this.customer_number = customer_number;
        this.payment = payment;
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
