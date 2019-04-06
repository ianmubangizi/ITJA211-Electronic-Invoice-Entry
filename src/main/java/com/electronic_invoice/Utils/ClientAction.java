package com.electronic_invoice.Utils;

import com.electronic_invoice.Entities.Queue;
import com.electronic_invoice.Entities.*;
import com.electronic_invoice.Frames.InvoiceEntry;
import com.electronic_invoice.Frames.Transaction;

import javax.swing.*;
import java.awt.*;

import static com.electronic_invoice.Services.Adders.AddAccount.accountService;
import static com.electronic_invoice.Services.Adders.AddCustomer.customerService;
import static com.electronic_invoice.Services.Adders.AddInvoice.invoiceService;
import static com.electronic_invoice.Services.Adders.AddLineItem.lineItemService;
import static com.electronic_invoice.Services.Finders.FindAccount.findAccount;
import static com.electronic_invoice.Services.Finders.FindCustomer.findCustomer;
import static com.electronic_invoice.Services.Finders.FindInvoice.findInvoice;
import static com.electronic_invoice.Services.Finders.FindProduct.findProduct;
import static com.electronic_invoice.Services.PrintInvoice.printService;
import static com.electronic_invoice.Utils.CustomerAlreadyExistsException.caee;

/**
 * Action
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class ClientAction {

    private Helpers makeUseOf = new Helpers();

    //

    /**
     * @param ief
     */
    public void addCustomer(InvoiceEntry ief) {
        int id = Integer.parseInt(
                ief.jtf_customernumber.getText().isEmpty()
                        ? "-1" : ief.jtf_customernumber.getText()
        );
        try {
            if (!findCustomer().findId(id)) {
                customerService().create(new Customer(id,
                        ief.jtf_name.getText(), ief.jtf_address.getText(),
                        ief.jtf_city.getText(),
                        ief.jtf_province.getText(),
                        ief.jtf_zip.getText(),
                        Double.valueOf(ief.jtf_deposit.getText())));
                makeUseOf.displayCustomerNumber(ief);
                return;
            }
            throw caee();
        } catch (NumberFormatException | CustomerAlreadyExistsException e) {
            makeUseOf.displayAnyErrors(e);
        }
    }

    //

    /**
     * @param ief
     */
    public void createInvoice(InvoiceEntry ief) {
        try {
            invoiceService().create(new Invoice(
                    Integer.parseInt(
                            (ief.jtf_invoicenumber.getText().isEmpty())
                                    ? "1" : ief.jtf_invoicenumber.getText()),
                    Integer.parseInt(ief.jtf_customernumber.getText()),
                    Double.valueOf("0.0")));
        } catch (NumberFormatException e) {
            makeUseOf.displayAnyErrors(e);
            return;
        }
        makeUseOf.displayInvoiceInfo(ief);
    }

    //

    /**
     * @param ief
     */
    public void showInvoice(InvoiceEntry ief) {
        try {
            Customer customer = findCustomer()
                    .getCustomer(findCustomer().withInvoiceId(
                            Integer.parseInt(ief.jtf_invoicenumber.getText())));
            Invoice invoice = findInvoice().getInvoice(
                    customer.getCustomer_number());
            ief.jtf_name.setText(customer.getName());
            ief.jtf_address.setText(customer.getAddress());
            ief.jtf_city.setText(customer.getCity());
            ief.jtf_province.setText(customer.getProvince());
            ief.jtf_zip.setText(customer.getZip());
            ief.jtf_customernumber.setText(String.valueOf(
                    customer.getCustomer_number()));
            ief.jtf_deposit.setText(String.valueOf(customer.getDeposit()));
            ief.jtxta_products.setText(makeUseOf.lineItemString(
                    Integer.parseInt(ief.jtf_invoicenumber.getText()),
                    ECallTypes.WRAP));
            ief.jtf_payment.setText(String.valueOf(invoice.getPayment()));
        } catch (NumberFormatException e) {
            makeUseOf.displayAnyErrors(e);
        }
    }

    //

    /**
     * @param ief
     */
    public void printInvoice(InvoiceEntry ief) {
        try {
            printService().print(
                    String.format(
                            "**********************************************************\n"
                                    + "Name: %s\n"
                                    + "Address: %s\n"
                                    + "City: %s\n"
                                    + "Province: %s\n"
                                    + "Zip %s\n"
                                    + "Deposit: %s\n"
                                    + "Product Bought: %s"
                                    + "Customer Number: %s\n"
                                    + "Invoice Number: %s\n"
                                    + "**********************************************************",
                            ief.jtf_name.getText(),
                            ief.jtf_address.getText(),
                            ief.jtf_city.getText(),
                            ief.jtf_province.getText(),
                            ief.jtf_zip.getText(),
                            ief.jtf_deposit.getText(), makeUseOf.lineItemString(
                                    Integer.parseInt(ief.jtf_invoicenumber.getText()),
                                    ECallTypes.WITH_TABS),
                            ief.jtf_customernumber.getText(),
                            ief.jtf_invoicenumber.getText()),
                    ief.jtf_customernumber.getText());
        } catch (NumberFormatException e) {
            makeUseOf.displayAnyErrors(e);
        }
    }

    //

    /**
     * @param ief
     */
    public void findProductAction(InvoiceEntry ief) {
        Product product = findProduct().withId((ief.jtf_productcode.getText()));
        ief.jtf_description.setText(product.getDescription());
        ief.jtf_price.setText(String.valueOf(product.getPrice()));
    }

    //

    /**
     * @param tsf
     * @param ief
     */
    public void checkAccount(Transaction tsf, InvoiceEntry ief) {
        try {
            Account account = findAccount().getById(
                    Integer.parseInt(tsf.jtf_customernumber.getText().isEmpty()
                            ? ief.jtf_customernumber.getText()
                            : tsf.jtf_customernumber.getText()));

            tsf.jtf_name.setText(account.getName());
            tsf.jtf_customernumber.setText(String.valueOf(account.getCustomer_number()));
            tsf.jtf_balance.setText(String.valueOf(account.getBalance()));
        } catch (NumberFormatException e) {
            makeUseOf.displayAnyErrors(e);
        }
    }

    //

    /**
     * @param tsf
     */
    public void addAccount(Transaction tsf) {
        try {
            accountService().create(new Account(tsf.jtf_name.getText(),
                    (findCustomer().findId(
                            Integer.parseInt(tsf.jtf_customernumber.getText()))
                            ? Integer.parseInt(tsf.jtf_customernumber.getText())
                            : -1),
                    Double.valueOf(tsf.jtf_balance.getText())));
        } catch (NumberFormatException e) {
            makeUseOf.displayAnyErrors(e);
        }
    }

    //

    /**
     *
     */
    public void calculateAndDeposit() {
        try {
            int count = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of Products: "));
            makeUseOf.receiveProducts(count);

            Transaction.getFrame().jtf_balance.setText(String.valueOf(makeUseOf.getDeposit()));
            InvoiceEntry.getFrame().jtf_deposit.setText(String.valueOf(makeUseOf.getDeposit()));
            InvoiceEntry.getFrame().jtf_payment.setText(String.valueOf(makeUseOf.getTotal()));
        } catch (HeadlessException | NumberFormatException e) {
            makeUseOf.displayAnyErrors(e);
        }
    }

    /**
     *
     */
    public void transaction() {
        makeUseOf.confirmTransaction();
        makeUseOf.getTransactions().forEach((Queue t) -> {
            accountService().update("balance",
                    String.valueOf(t.getDeposit()),
                    findCustomer().withInvoiceId(Integer.valueOf(t.getInvoiceId()))
            );
            customerService().update("deposit",
                    String.valueOf(t.getDeposit()),
                    findCustomer().withInvoiceId(
                            Integer.valueOf(t.getInvoiceId())
                    )
            );
            invoiceService().update("payment",
                    String.valueOf(t.getPayment()),
                    Integer.valueOf(t.getInvoiceId())
            );
        });
        makeUseOf.getItems().forEach(i -> lineItemService().create(i));
    }
}
