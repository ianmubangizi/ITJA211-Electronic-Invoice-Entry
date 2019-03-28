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
import com.electronic_invoice.Services.Adders.AddLineItem;
import com.electronic_invoice.Services.Finders.FindAccount;
import com.electronic_invoice.Services.Finders.FindCustomer;
import com.electronic_invoice.Services.Finders.FindInvoice;
import com.electronic_invoice.Services.Finders.FindProduct;
import com.electronic_invoice.Services.PrintInvoice;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Action
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class ClientAction {

    Helpers makeUseOf = new Helpers();

    //
    public void addCustomer(InvoiceEntry ief) {
        try {
            new AddCustomer().create(new Customer(
                    Integer.parseInt(
                            (ief.jtf_customernumber.getText().isEmpty()) ? "1" : ief.jtf_customernumber.getText()),
                    ief.jtf_name.getText(), ief.jtf_address.getText(), ief.jtf_city.getText(),
                    ief.jtf_province.getText(), ief.jtf_zip.getText(), Double.valueOf(ief.jtf_deposit.getText())));
        } catch (NumberFormatException e) {
            makeUseOf.displayAnyErrors(e);
            return;
        }
        makeUseOf.displayCustomerNumber(ief);
    }

    //
    public void createInvoice(InvoiceEntry ief) {
        try {
            new AddInvoice().create(new Invoice(
                    Integer.parseInt(
                            (ief.jtf_invoicenumber.getText().isEmpty()) ? "1" : ief.jtf_invoicenumber.getText()),
                    Integer.parseInt(ief.jtf_customernumber.getText()),
                    Double.valueOf(ief.jtf_payment.getText().isEmpty() ? "0.0" : ief.jtf_payment.getText())));
        } catch (NumberFormatException e) {
            makeUseOf.displayAnyErrors(e);
            return;
        }
        makeUseOf.displayInvoiceInfo(ief);
    }

    //
    public void showInvoice(InvoiceEntry ief) {
        try {
            Customer customer = new FindCustomer()
                    .getCustomer(new FindCustomer().withInvoiceId(Integer.parseInt(ief.jtf_invoicenumber.getText())));
            Invoice invoice = new FindInvoice().getInvoice(customer.getCustomer_number());
            ief.jtf_name.setText(customer.getName());
            ief.jtf_address.setText(customer.getAddress());
            ief.jtf_city.setText(customer.getCity());
            ief.jtf_province.setText(customer.getProvince());
            ief.jtf_zip.setText(customer.getZip());
            ief.jtf_customernumber.setText(String.valueOf(customer.getCustomer_number()));
            ief.jtf_deposit.setText(String.valueOf(customer.getDeposit()));
            ief.jtxta_products.setText(makeUseOf.itemString(Integer.parseInt(ief.jtf_invoicenumber.getText())));
            ief.jtf_payment.setText(String.valueOf(invoice.getPayment()));
        } catch (Exception e) {
            makeUseOf.displayAnyErrors(e);
        }
    }

    //
    public void printInvoice(InvoiceEntry ief) {
        try {
            new PrintInvoice().print("**********************************************************\n" + "Name: "
                    + ief.jtf_name.getText() + " \n" + "Address: " + ief.jtf_address.getText() + " \n" + "City: "
                    + ief.jtf_city.getText() + " \n" + "Province: " + ief.jtf_province.getText() + " \n" + "Zip "
                    + ief.jtf_zip.getText() + " \n" + "Deposit: " + ief.jtf_deposit.getText() + " \n"
                    + "Product Bought: " + makeUseOf.itemString(Integer.parseInt(ief.jtf_invoicenumber.getText()))
                    + "Customer Number: " + ief.jtf_customernumber.getText() + " \n" + "Invoice Number: "
                    + ief.jtf_invoicenumber.getText() + " \n"
                    + "**********************************************************", ief.jtf_customernumber.getText());
        } catch (NumberFormatException e) {
            makeUseOf.displayAnyErrors(e);
        }
    }

    //
    public void findProduct(InvoiceEntry ief) {
        Product product = new FindProduct().withProductId(ief.jtf_productcode.getText());
        ief.jtf_description.setText(product.getDescription());
        ief.jtf_price.setText(String.valueOf(product.getPrice()));
    }

    //
    public void checkAccount(Transaction tsf, InvoiceEntry ief) {
        try {
            Account account = new FindAccount().getById(
                    Integer.parseInt(tsf.jtf_customernumber.getText().isEmpty() ? ief.jtf_customernumber.getText()
                            : tsf.jtf_customernumber.getText()));

            tsf.jtf_name.setText(account.getName());
            tsf.jtf_customernumber.setText(String.valueOf(account.getCustomer_number()));
            tsf.jtf_balance.setText(String.valueOf(account.getBalance()));
        } catch (NumberFormatException e) {
            makeUseOf.displayAnyErrors(e);
        }
    }

    //
    public void addAccount(Transaction tsf) {
        try {
            new AddAccount().create(new Account(tsf.jtf_name.getText(),
                    (new FindCustomer().byId(Integer.parseInt(tsf.jtf_customernumber.getText()))
                            ? Integer.parseInt(tsf.jtf_customernumber.getText())
                            : null),
                    Double.valueOf(tsf.jtf_balance.getText())));
        } catch (NumberFormatException e) {
            makeUseOf.displayAnyErrors(e);
        }
    }

    //
    public void calculateAndDeposit(Transaction tsf) {
        JTextField price = new JTextField();
        JTextField invoice_id = new JTextField();
        JTextField quantity = new JTextField();
        JComboBox<String> list = new JComboBox<>();
        ArrayList<LineItem> items = new ArrayList<>();
        ArrayList<Product> products = new FindProduct().allProducts();

        double payment = 0.0;
        double deposit = 0.0;

        Object[] str = { "Select Product", list, "Quantity", quantity, "Price", price, "Invoice Number", invoice_id };

        try {
            invoice_id.setText(String.valueOf(invoice_id));

            int count = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of Products: "));

            products.forEach((product) -> {
                list.addItem(product.toString());
            });

            for (int i = 1; i <= count; i++) {
                JOptionPane.showMessageDialog(null, str, "Product [" + i + "]", JOptionPane.PLAIN_MESSAGE);
                items.add(new LineItem(Integer.parseInt(invoice_id.getText()),
                        products.get(list.getSelectedIndex()).getProduct_code(), Integer.parseInt(quantity.getText())));
                payment = payment + (Integer.parseInt(quantity.getText()) * Double.parseDouble(price.getText()));
            }

        } catch (HeadlessException | NumberFormatException e) {
            makeUseOf.displayAnyErrors(e);
        }

        items.forEach((item) -> {
            new AddLineItem().create(item);
        });
    }
}
