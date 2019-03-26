package com.electronic_invoice.Utils;

import com.electronic_invoice.Entities.Account;
import com.electronic_invoice.Entities.Customer;
import com.electronic_invoice.Entities.Invoice;
import com.electronic_invoice.Entities.LineItem;
import com.electronic_invoice.Entities.Product;
import com.electronic_invoice.Frames.InvoiceEntry;
import com.electronic_invoice.Frames.Transaction;
import com.electronic_invoice.Services.Adders.AddAccount;
import com.electronic_invoice.Services.Adders.AddCustomer;
import com.electronic_invoice.Services.Adders.AddInvoice;
import com.electronic_invoice.Services.Finders.FindAccount;
import com.electronic_invoice.Services.Finders.FindCustomer;
import com.electronic_invoice.Services.Finders.FindInvoice;
import com.electronic_invoice.Services.Finders.FindProduct;
import com.electronic_invoice.Services.PrintInvoice;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Action
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class ClientAction {

    Helpers makeUseOf = new Helpers();

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
            makeUseOf.displayError(e);
            return;
        }
        makeUseOf.displayCustomerNumber(ief);
    }

    //
    public void createInvoice(InvoiceEntry ief) {
        try {
            new AddInvoice(new Invoice(
                    Integer.parseInt((ief.jtf_invoicenumber.getText().isEmpty())
                            ? "1" : ief.jtf_invoicenumber.getText()),
                    Integer.parseInt(ief.jtf_customernumber.getText()),
                    Double.valueOf(ief.jtf_payment.getText().isEmpty() ? "0.0"
                            : ief.jtf_payment.getText())
            )
            );
        } catch (NumberFormatException e) {
            makeUseOf.displayError(e);
            return;
        }
        makeUseOf.displayInvoiceInfo(ief);
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
            Invoice invoice = new FindInvoice().getInvoice(
                    customer.getCustomer_number()
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
            ief.jtxta_products.setText(makeUseOf.makeItemString(
                    Integer.parseInt(ief.jtf_invoicenumber.getText()))
            );
            ief.jtf_payment.setText(String.valueOf(invoice.getPayment()));
        } catch (NumberFormatException e) {
            makeUseOf.displayError(e);
        }
    }

    //
    public void printInvoice(InvoiceEntry ief) {
        new PrintInvoice().print(
                "**********************************************************\n"
                + "Name: " + ief.jtf_name.getText() + " \n"
                + "Address: " + ief.jtf_address.getText() + " \n"
                + "City: " + ief.jtf_city.getText() + " \n"
                + "Provice: " + ief.jtf_province.getText() + " \n"
                + "Zip " + ief.jtf_zip.getText() + " \n"
                + "Deposit: " + ief.jtf_deposit.getText() + " \n"
                + "Product Bought: " + makeUseOf.makeItemString(
                        Integer.parseInt(ief.jtf_invoicenumber.getText())
                )
                + "Customer Number: " + ief.jtf_customernumber.getText() + " \n"
                + "Invoice Number: " + ief.jtf_invoicenumber.getText() + " \n"
                + "**********************************************************",
                ief.jtf_customernumber.getText()
        );
    }

    //
    public void findProduct(InvoiceEntry ief) {
        Product product = new FindProduct().withProductId(
                ief.jtf_productcode.getText()
        );
        ief.jtf_description.setText(product.getDescription());
        ief.jtf_price.setText(String.valueOf(product.getPrice()));
    }

    //
    public void checkAccount(Transaction tsf, InvoiceEntry ief) {
        Account account = new FindAccount().getById(Integer.parseInt(
                tsf.jtf_customernumber.getText().isEmpty()
                ? ief.jtf_customernumber.getText()
                : tsf.jtf_customernumber.getText())
        );

        tsf.jtf_name.setText(account.getName());
        tsf.jtf_customernumber.setText(String.valueOf(
                account.getCustomer_number())
        );
        tsf.jtf_balance.setText(String.valueOf(account.getBalance()));
    }

    public void addAccount(Transaction tsf) {
        new AddAccount().create(new Account(tsf.jtf_name.getText(),
                (new FindCustomer().byId(Integer.parseInt(
                        tsf.jtf_customernumber.getText()))
                ? Integer.parseInt(tsf.jtf_customernumber.getText()) : null),
                Double.valueOf(tsf.jtf_balance.getText()))
        );
    }

    public void calcuteAndDeposit() {
        ArrayList<LineItem> items = new ArrayList<>();
        int quantity;
        double price;
        int products = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Number of Products Bought")
        );
        while(products > 0){
            items.add(new LineItem());
            products--;
        }
    }
}
