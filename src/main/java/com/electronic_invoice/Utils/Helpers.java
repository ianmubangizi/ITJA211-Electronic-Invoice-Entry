/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice.Utils;

import com.electronic_invoice.Entities.Invoice;
import com.electronic_invoice.Frames.InvoiceEntry;
import com.electronic_invoice.Services.Finders.FindCustomer;
import com.electronic_invoice.Services.Finders.FindInvoice;
import com.electronic_invoice.Services.Finders.FindLineItem;
import com.electronic_invoice.Services.Finders.FindProduct;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class Helpers {

    //
    public String makeItemString(int id) {
        String product_list = "";
        product_list = new FindLineItem().withInvoiceId(id).stream().map(
                (product) -> product.getProduct_code() + " x "
                + product.getQuantity() + "\n").reduce(
                        product_list, String::concat
                );
        return product_list;
    }

    //
    public void displayProductList(InvoiceEntry ief) {
        new FindProduct().allProducts().forEach((product) -> {
            ief.jcbx_allproducts.addItem(product.getDescription());
        });
    }

    //
    public void displayCustomerNumber(InvoiceEntry ief) {
        ief.jtf_customernumber.setText(String.valueOf(
                new FindCustomer().lastCreatedId())
        );
    }

    //
    public void displayInvoiceInfo(InvoiceEntry ief) {
        Invoice invoice = new FindInvoice().getInvoice(Integer.parseInt(
                ief.jtf_customernumber.getText()));
        ief.jtf_invoicenumber.setText(String.valueOf(invoice.getInvoice_number())
        );
        ief.jtf_payment.setText(String.valueOf(invoice.getPayment()));
    }

    //
    public void displayError(Exception e) {
        if (NumberFormatException.class.isInstance(e)) {
            new MessagePane(null, "Please Input Correct values into Form fields",
                    "Invaild Inputs [Empty Values]", MessagePane.EMessage.ERROR);
        }
        System.out.println(e.getMessage());
    }
}
