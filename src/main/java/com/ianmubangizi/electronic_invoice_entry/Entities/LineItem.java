/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ianmubangizi.electronic_invoice_entry.Entities;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class LineItem {
    private int invoice_number;
    private String product_code;
    private int quantity;

    public LineItem(int invoice_number, String product_code, int quantity) {
        this.invoice_number = invoice_number;
        this.product_code = product_code;
        this.quantity = quantity;
    }

    public LineItem() {
    }

    public int getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(int invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
