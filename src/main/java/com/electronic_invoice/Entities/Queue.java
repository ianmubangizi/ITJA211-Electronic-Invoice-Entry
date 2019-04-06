package com.electronic_invoice.Entities;

/**
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class Queue {
    private String queue_invoiceid;
    private double payment;
    private double deposit;

    /**
     * @param queue_invoice
     * @param payment
     * @param deposit
     */
    public Queue(String queue_invoice, double payment, double deposit) {
        this.queue_invoiceid = queue_invoice;
        this.payment = payment;
        this.deposit = deposit;
    }

    /**
     * @return
     */
    public String getInvoiceId() {
        return queue_invoiceid;
    }

    /**
     * @param queue_invoiceid
     */
    public void setInvoiceId(String queue_invoiceid) {
        this.queue_invoiceid = queue_invoiceid;
    }

    /**
     * @return
     */
    public double getPayment() {
        return payment;
    }

    /**
     * @param payment
     */
    public void setPayment(double payment) {
        this.payment = payment;
    }

    /**
     * @return
     */
    public double getDeposit() {
        return deposit;
    }

    /**
     * @param deposit
     */
    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
}
