package com.electronic_invoice.Utils;

import com.electronic_invoice.Entities.Invoice;
import com.electronic_invoice.Entities.LineItem;
import com.electronic_invoice.Entities.Product;
import com.electronic_invoice.Entities.Queue;
import com.electronic_invoice.Frames.InvoiceEntry;
import com.electronic_invoice.Frames.Transaction;
import static com.electronic_invoice.Services.Finders.FindCustomer.findCustomer;
import static com.electronic_invoice.Services.Finders.FindInvoice.findInvoice;
import static com.electronic_invoice.Services.Finders.FindLineItem.findLineItem;
import static com.electronic_invoice.Services.Finders.FindProduct.findProduct;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class Helpers {

    double payment;
    double total = 0;
    double deposit;
    JTextField price = new JTextField();
    JTextField invoice_id = new JTextField();
    JTextField quantity = new JTextField();
    JComboBox<String> list = new JComboBox<>();
    ArrayList<LineItem> items = new ArrayList<>();
    ArrayList<Object[]> displays = new ArrayList<>();
    ArrayList<Queue> transactions = new ArrayList<>();
    ArrayList<Product> products = findProduct().allProducts();

    //
    /**
     *
     * @param count
     */
    public void receiveProducts(int count) {
        products.forEach((product) -> {
            list.addItem(product.toString());
        });
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
            items.add(new LineItem(Integer.parseInt(invoice_id.getText()),
                    products.get(list.getSelectedIndex()).getProduct_code(),
                    Integer.parseInt(quantity.getText()))
            );
            if (!invoice_id.getText().isEmpty() && i == 1) {
                transactions.add(new Queue(invoice_id.getText(), 0,
                        Double.valueOf(Transaction.getFrame().jtf_balance.getText()))
                );
            }
            transactions.stream().map((t) -> {
                payment = Integer.parseInt(quantity.getText()) * Double.parseDouble(price.getText());
                return t;
            }).map((t) -> {
                deposit = deposit - payment;
                return t;
            }).map((t) -> {
                total = total + payment;
                return t;
            }).map((t) -> {
                t.setDeposit(deposit);
                return t;
            }).forEachOrdered((t) -> {
                t.setPayment(total);
            });
        }
    }

    // s
    /**
     *
     */
    public void confirmTransaction() {
        items.forEach((item) -> {
            Product product = findProduct().withId(item.getProduct_code());
            Object obj[] = {"Product: " + product.getDescription(), "Quantity: " + item.getQuantity(),
                "Price R" + product.getPrice(), "Total Payment: " + (product.getPrice() * item.getQuantity()),
                "Invoice Number: " + item.getInvoice_number(),
                "\n------------------------------------------------------------"};
            displays.add(obj);
        });
        JOptionPane.showMessageDialog(null, displays.toArray(), "Accounts Payable", JOptionPane.PLAIN_MESSAGE);
        displays.clear();
    }

    //
    /**
     *
     * @param id
     * @param t
     * @return
     */
    public String lineItemString(int id, ECallTypes t) {
        String product_list = "";
        ArrayList<LineItem> _items = findLineItem().withInvoiceId(id);
        int i = 0;
        switch (t) {
            case WITH_TABS:
                for (LineItem item : _items) {
                    i++;
                    product_list += (i == _items.size() ? item.getProduct_code() + " x " + item.getQuantity() + "\n"
                            : item.getProduct_code() + " x " + item.getQuantity() + "\n\t\t");
                }
                break;
            case WRAP:
                product_list = _items.stream().map((item) -> item.getProduct_code() + " x " + item.getQuantity() + "\n")
                        .reduce(product_list, String::concat);
            case ADD_CUSTOMER:
            case ADD_INVOICE_CUSTOMER:
            case CUSTOMERID_TAKEN:
            case NEED_VAILD_CUSTOMERID:
        }
        return product_list;
    }

    //
    /**
     *
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
     *
     * @param ief
     */
    public void displayProductList(InvoiceEntry ief) {
        findProduct().allProducts().forEach((product) -> {
            ief.jcbx_allproducts.addItem(product.getDescription());
        });
    }

    //
    /**
     *
     * @param ief
     */
    public void displayCustomerNumber(InvoiceEntry ief) {
        ief.jtf_customernumber.setText(String.valueOf(findCustomer().lastCreatedId()));
    }

    //
    /**
     *
     * @param ief
     */
    public void displayInvoiceInfo(InvoiceEntry ief) {
        Invoice invoice = findInvoice().getInvoice(Integer.parseInt(ief.jtf_customernumber.getText()));
        ief.jtf_invoicenumber.setText(String.valueOf(invoice.getInvoice_number()));
        ief.jtf_payment.setText(String.valueOf(invoice.getPayment()));
    }

    //
    /**
     *
     * @param e
     */
    public void displayAnyErrors(Exception e) {
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
}
