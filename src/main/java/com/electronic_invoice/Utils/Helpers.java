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
    public String itemString(int id) {
        String product_list = "";
        product_list = new FindLineItem().withInvoiceId(id).stream().map(
                (product) -> product.getProduct_code() + " x "
                + product.getQuantity() + "\n").reduce(
                        product_list, String::concat
                );
        return product_list;
    }

    //
    public void addCustomer(ECallTypes t) {
        switch (t) {
            case ADD_CUSTOMER:
                new ClientAction().addCustomer(
                        InvoiceEntry.getTransactionFrame()
                );
                break;
            case CUSTOMERID_TAKEN:
                new MessagePane(null,
                        "The ", "Adding Customer Error", MessagePane.EMessage.ERROR);
                break;
            case NEED_VAILD_CUSTOMERID:
                new MessagePane(null,
                        "Can't Create Invoice without a vaild Customer Number\n"
                        + "Press Okay and Enter Customer Info to get a Number\n"
                        + "Click the Add Customer Button after inputting Customer Info",
                        "Adding Invoice Error", MessagePane.EMessage.ERROR);
                break;
            case ADD_INVOICE_CUSTOMER:
                new MessagePane(null,
                        "Enter your Customer Details then\n"
                        + "Click the Add Customer Button then\n"
                        + "Try Adding an Invoice",
                        "Need a Customer Number - When Adding Invoice",
                        MessagePane.EMessage.INFO);
                break;
            default:
                break;
        }
    }

    //
    public void displayProductList(InvoiceEntry ief) {
        new FindProduct().allProducts().forEach((product) -> {
            ief.jcbx_allproducts.addItem(product.getDescription() + "  [Code:  "
                    + product.getProduct_code() + "]");
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
    public void displayAnyErrors(Exception e) {
        String msg = "";
        switch (e.getMessage()) {
            case "For input string: \"\"":
            case "empty String":
                msg = "Field Has Empty Value";
                break;
            default:
                break;
        }
        new MessagePane(null, "Please Input Correct values into Form fields",
                "Invaild Input [" + msg + "]", MessagePane.EMessage.ERROR);
    }
}
