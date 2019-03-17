package com.electronic_invoice.Utils;

import com.electronic_invoice.Entities.Customer;
import com.electronic_invoice.Entities.Invoice;
import com.electronic_invoice.Entities.Product;
import com.electronic_invoice.Frames.InvoiceEntry;
import com.electronic_invoice.Services.AddCustomer;
import com.electronic_invoice.Services.AddInvoice;
import com.electronic_invoice.Services.FindCustomer;
import com.electronic_invoice.Services.FindInvoice;
import com.electronic_invoice.Services.FindLineItem;
import com.electronic_invoice.Services.FindProduct;
import com.electronic_invoice.Services.PrintInvoice;
import com.electronic_invoice.Utils.MessagePane.EMessage;

/**
 * Action
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class ClientAction {

    //
    public void createCustomer(InvoiceEntry ief) {
        try {
            new AddCustomer(new Customer(
                    Integer.parseInt(
                            (ief.jtf_customernumber.getText().isEmpty())
                            ? "1" : ief.jtf_customernumber.getText()),
                    ief.jtf_name.getText(),
                    ief.jtf_address.getText(),
                    ief.jtf_city.getText(),
                    ief.jtf_province.getText(),
                    ief.jtf_zip.getText(),
                    Double.valueOf(ief.jtf_deposit.getText())));
        } catch (NumberFormatException e) {
            displayError(e);
            return;
        }
        displayCustomerNumber(ief);
    }

    //
    public void createInvoice(InvoiceEntry ief) {
        try {
            new AddInvoice(new Invoice(
                    Integer.parseInt((ief.jtf_invoicenumber.getText().isEmpty())
                            ? "1" : ief.jtf_invoicenumber.getText()),
                    Integer.parseInt(ief.jtf_customernumber.getText()),
                    Double.valueOf(ief.jtf_payment.getText())
            )
            );
        } catch (NumberFormatException e) {
            displayError(e);
            return;
        }
        displayInvoiceNumber(ief);
    }

    //
    public void showInvoice(InvoiceEntry ief) {
        try {
            Customer customer = new FindCustomer().getCustomer(
                    new FindCustomer().withInvoiceId(
                            Integer.parseInt(
                                    ief.jtf_invoicenumber.getText()
                            )
                    )
            );
            ief.jtf_name.setText(customer.getName());
            ief.jtf_address.setText(customer.getAddress());
            ief.jtf_city.setText(customer.getCity());
            ief.jtf_province.setText(customer.getProvince());
            ief.jtf_zip.setText(customer.getZip());
            ief.jtf_customernumber.setText(String.valueOf(
                    customer.getCustomer_number())
            );
            ief.jtf_deposit.setText(String.valueOf(customer.getDeposit()));
            ief.jtxta_products.setText(productItemString(
                    Integer.parseInt(ief.jtf_invoicenumber.getText()))
            );
        } catch (NumberFormatException e) {
            displayError(e);
        }
    }

    //
    public void printInvoice(InvoiceEntry ief) {
        new PrintInvoice().print("**********************************************************\n"
                + "Name: " + ief.jtf_name.getText() + " \n"
                + "Address: " + ief.jtf_address.getText() + " \n"
                + "City: " + ief.jtf_city.getText() + " \n"
                + "Provice: " + ief.jtf_province.getText() + " \n"
                + "Zip " + ief.jtf_zip.getText() + " \n"
                + "Deposit: " + ief.jtf_deposit.getText() + " \n"
                + "Product Bought: " + productItemString(
                        Integer.parseInt(ief.jtf_invoicenumber.getText())
                )
                + "Customer Number: " + ief.jtf_customernumber.getText() + " \n"
                + "Invoice Number: " + ief.jtf_invoicenumber.getText() + " \n"
                + "**********************************************************",
                ief.jtf_customernumber.getText()
        );
    }

    //
    public void displayProductList(InvoiceEntry ief){
        for (Product product : new FindProduct().allProducts()) {
            ief.jcbx_allproducts.addItem(product.getDescription());
        }
    }

    //
    private void displayCustomerNumber(InvoiceEntry ief) {
        ief.jtf_customernumber.setText("" + new FindCustomer().lastCreatedId() + "");
    }

    //
    private void displayInvoiceNumber(InvoiceEntry ief) {
        ief.jtf_invoicenumber.setText(
                "" + new FindInvoice().withQuery(
                        "SELECT invoice_number FROM orion.invoice WHERE customer_number="
                        + ief.jtf_customernumber.getText() + ";") + "");
    }

    //
    public String productItemString(int id) {
        String product_list = "";
        product_list = new FindLineItem().withInvoiceId(id).stream().map(
                (product) -> product.getProduct_code() + " x "
                + product.getQuantity() + "\n").reduce(
                        product_list, String::concat
                );
        return product_list;
    }

    //
    private void displayError(Exception e) {
        if (NumberFormatException.class.isInstance(e)) {
            new MessagePane(null, "Please Input Correct values into Form fields",
                    "Invaild Inputs [Empty Values]", EMessage.ERROR);
        }
        System.out.println(e.getMessage());
    }
}
