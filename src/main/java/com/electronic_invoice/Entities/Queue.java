package com.electronic_invoice.Entities;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class Queue {
    private String queue_invoiceid;
    private double payment;
    private double deposit;

    /**
     *
     * @param queue_invoiceid
     * @param payment
     * @param deposit
     */
    public Queue(String queue_invoiceid, double payment, double deposit) {
        this.queue_invoiceid = queue_invoiceid;
        this.payment = payment;
        this.deposit = deposit;
    }

    /**
     *
     * @return
     */
    public String getInvoiceId() {
        return queue_invoiceid;
    }

    /**
     *
     * @param queue_invoiceid
     */
    public void setInvoiceId(String queue_invoiceid) {
        this.queue_invoiceid = queue_invoiceid;
    }

    /**
     *
     * @return
     */
    public double getPayment() {
        return payment;
    }

    /**
     *
     * @param payment
     */
    public void setPayment(double payment) {
        this.payment = payment;
    }

    /**
     *
     * @return
     */
    public double getDeposit() {
        return deposit;
    }

    /**
     *
     * @param deposit
     */
    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
}
