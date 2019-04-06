package com.electronic_invoice.Entities;

/**
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class LineItem {

    private int invoice_number;
    private String product_code;
    private int quantity;

    /**
     * @param invoice_number
     * @param product_code
     * @param quantity
     */
    public LineItem(int invoice_number, String product_code, int quantity) {
        this.invoice_number = invoice_number;
        this.product_code = product_code;
        this.quantity = quantity;
    }

    /**
     *
     */
    public LineItem() {
    }

    /**
     * @return
     */
    public int getInvoice_number() {
        return invoice_number;
    }

    /**
     * @param invoice_number
     */
    public void setInvoice_number(int invoice_number) {
        this.invoice_number = invoice_number;
    }

    /**
     * @return
     */
    public String getProduct_code() {
        return product_code;
    }

    /**
     * @param product_code
     */
    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    /**
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
