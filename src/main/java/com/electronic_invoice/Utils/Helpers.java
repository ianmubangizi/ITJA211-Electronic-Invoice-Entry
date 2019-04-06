package com.electronic_invoice.Utils;

import com.electronic_invoice.Entities.Invoice;
import com.electronic_invoice.Entities.LineItem;
import com.electronic_invoice.Entities.Product;
import com.electronic_invoice.Entities.Queue;
import com.electronic_invoice.Frames.InvoiceEntry;
import com.electronic_invoice.Frames.Transaction;

import javax.swing.*;
import java.util.ArrayList;

import static com.electronic_invoice.Services.Finders.FindCustomer.findCustomer;
import static com.electronic_invoice.Services.Finders.FindInvoice.findInvoice;
import static com.electronic_invoice.Services.Finders.FindLineItem.findLineItem;
import static com.electronic_invoice.Services.Finders.FindProduct.findProduct;

/**
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class Helpers {

    private double payment;
    private double total = 0;
    private double deposit;
    private JTextField price = new JTextField();
    private JTextField invoice_id = new JTextField();
    private JTextField quantity = new JTextField();
    private JComboBox<String> list = new JComboBox<>();
    private ArrayList<LineItem> items = new ArrayList<>();
    private ArrayList<Object[]> displays = new ArrayList<>();
    private ArrayList<Queue> transactions = new ArrayList<>();
    private ArrayList<Product> products = findProduct().allProducts();

    //

    /**
     * @param count
     */
    void receiveProducts(int count) {
        products.forEach((product) -> list.addItem(product.toString()));
        list.addActionListener((e) -> price.setText(String.valueOf(products.get(
                list.getSelectedIndex()).getPrice()))
        );
        deposit = Double.valueOf(Transaction.getFrame().jtf_balance.getText());
        invoice_id.setText(InvoiceEntry.getFrame().jtf_invoicenumber.getText());
        Object[] inputs = {
                "Select Product", list,
                "Quantity", quantity,
                "Price", price,
                "Invoice Number", invoice_id
        };
        for (int i = 1; i <= count; i++) {
            JOptionPane.showMessageDialog(null, inputs, "Product [" + i + "]", JOptionPane.PLAIN_MESSAGE);
            items.add(new LineItem(
                    Integer.parseInt(invoice_id.getText()),
                    products.get(list.getSelectedIndex()).getProduct_code(),
                    Integer.parseInt(quantity.getText()))
            );
            if (!invoice_id.getText().isEmpty() && i == 1) {
                transactions.add(new Queue(invoice_id.getText(), 0,
                        Double.valueOf(Transaction.getFrame().jtf_balance.getText()))
                );
            }
            transactions.stream().peek((t) -> payment = Integer.parseInt(
                    quantity.getText()) * Double.parseDouble(
                    price.getText())).peek(
                    (t) -> deposit = deposit - payment).peek(
                    (t) -> total = total + payment).peek(
                    (t) -> t.setDeposit(deposit)).forEachOrdered(
                    (t) -> t.setPayment(total));
        }
    }

    // s

    /**
     *
     */
    void confirmTransaction() {
        items.forEach((item) -> {
            Product product = findProduct().withId(item.getProduct_code());
            Object[] obj = {
                    "Product: " + product.getDescription(),
                    "Quantity: " + item.getQuantity(),
                    "Price R" + product.getPrice(),
                    "Total Payment: " + (product.getPrice() * item.getQuantity()),
                    "Invoice Number: " + item.getInvoice_number(),
                    "\n------------------------------------------------------------"
            };
            displays.add(obj);
        });
        JOptionPane.showMessageDialog(null, displays.toArray(), "Accounts Payable", JOptionPane.PLAIN_MESSAGE);
        displays.clear();
    }

    //

    /**
     * @param id
     * @param t
     * @return
     */
    String lineItemString(int id, ECallTypes t) {
        StringBuilder product_list = new StringBuilder();
        ArrayList<LineItem> _items = findLineItem().withInvoiceId(id);
        int i = 0;
        switch (t) {
            case WITH_TABS:
                for (LineItem item : _items) {
                    i++;
                    product_list.append(i == _items.size() ? item.getProduct_code() + " x " + item.getQuantity() + "\n"
                            : item.getProduct_code() + " x " + item.getQuantity() + "\n\t\t");
                }
                break;
            case WRAP:
                product_list = new StringBuilder(_items.stream().map((item) -> item.getProduct_code() + " x " + item.getQuantity() + "\n")
                        .reduce(product_list.toString(), String::concat));
            case ADD_CUSTOMER:
            case ADD_INVOICE_CUSTOMER:
            case CUSTOMERID_TAKEN:
            case NEED_VAILD_CUSTOMERID:
        }
        return product_list.toString();
    }

    //

    /**
     * @param t
     */
    public void addCustomer(ECallTypes t) {
        switch (t) {
            case ADD_CUSTOMER: {
                new ClientAction().addCustomer(InvoiceEntry.getFrame());
            }
            break;
            case CUSTOMERID_TAKEN:
                new MessagePane(null, "The ", "Adding Customer Error", MessagePane.EMessage.ERROR);
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
                        "Enter your Customer Details then\n" + "Click the Add Customer Button then\n"
                                + "Try Adding an Invoice",
                        "Need a Customer Number - When Adding Invoice", MessagePane.EMessage.INFO);
                break;
            default:
                break;
        }
    }

    //

    /**
     * @param ief
     */
    public void displayProductList(InvoiceEntry ief) {
        findProduct().allProducts().forEach((product) -> ief.jcbx_allproducts.addItem(product.getDescription()));
    }

    //

    /**
     * @param ief
     */
    void displayCustomerNumber(InvoiceEntry ief) {
        ief.jtf_customernumber.setText(String.valueOf(findCustomer().lastCreatedId()));
    }

    //

    /**
     * @param ief
     */
    void displayInvoiceInfo(InvoiceEntry ief) {
        Invoice invoice = findInvoice().getInvoice(Integer.parseInt(ief.jtf_customernumber.getText()));
        ief.jtf_invoicenumber.setText(String.valueOf(invoice.getInvoice_number()));
        ief.jtf_payment.setText(String.valueOf(invoice.getPayment()));
    }

    //

    /**
     * @param e
     */
    void displayAnyErrors(Exception e) {
        String msg = "";
        switch (e.getMessage()) {
            case "For input string: \"\"":
            case "empty String":
                msg = "Field Has Empty Value";
                break;
            case "Customer Already Exists":
                msg = "Customer Already Exists";
            default:
                break;
        }
        new MessagePane(null, "Please Input Correct values into Form fields", "Invaild Input [" + msg + "]",
                MessagePane.EMessage.ERROR);
    }

    double getTotal() {
        return total;
    }

    public double getDeposit() {
        return deposit;
    }

    ArrayList<LineItem> getItems() {
        return items;
    }

    ArrayList<Queue> getTransactions() {
        return transactions;
    }

}
