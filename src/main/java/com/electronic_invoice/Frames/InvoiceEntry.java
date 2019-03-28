/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice.Frames;

import com.electronic_invoice.Utils.ClientAction;
import com.electronic_invoice.Utils.Helpers;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class InvoiceEntry extends JFrame implements ActionListener {

    private static final long serialVersionUID = 2720710036607580794L;
    private final ClientAction action = new ClientAction();
    private static InvoiceEntry frame = null;
    private final String frame_title = "Electronic Invoice Entry";

    //
    private final JLabel jl_name = new JLabel("Name");
    private final JLabel jl_address = new JLabel("Address");
    private final JLabel jl_city = new JLabel("City");
    private final JLabel jl_description = new JLabel("Description");
    private final JLabel jl_province = new JLabel("Province");
    private final JLabel jl_zip = new JLabel("Zip");
    private final JLabel jl_productcode = new JLabel("Product Code");
    private final JLabel jl_invoicenumber = new JLabel("Invoice Number");
    private final JLabel jl_customernumber = new JLabel("Customer Number");
    private final JLabel jl_productbought = new JLabel("Product Bought");
    private final JLabel jl_quantity = new JLabel("Quantity");
    private final JLabel jl_payment = new JLabel("Payment");
    private final JLabel jl_price = new JLabel("Price");
    private final JLabel jl_deposit = new JLabel("Deposit");
    private final JLabel jl_allproducts = new JLabel("All Products:");

    //
    public final JTextField jtf_name = new JTextField();
    public final JTextField jtf_address = new JTextField();
    public final JTextField jtf_city = new JTextField();
    public final JTextField jtf_description = new JTextField();
    public final JTextField jtf_province = new JTextField();
    public final JTextField jtf_zip = new JTextField();
    public final JTextField jtf_productcode = new JTextField();
    public final JTextField jtf_invoicenumber = new JTextField();
    public final JTextField jtf_customernumber = new JTextField();
    public final JTextField jtf_quantity = new JTextField();
    public final JTextField jtf_payment = new JTextField();
    public final JTextField jtf_price = new JTextField();
    public final JTextField jtf_deposit = new JTextField();

    //
    public final JButton jbtn_addcustomer = new JButton("Add Customer");
    public final JButton jbtn_findproduct = new JButton("Find Product");
    public final JButton jbtn_listproduct = new JButton("List Product");
    public final JButton jbtn_addinvoice = new JButton("Add Invoice");
    public final JButton jbtn_showinvoice = new JButton("Show Invoice");
    public final JButton jbtn_exit = new JButton("Exit");
    public final JButton jbtn_writeinvoice = new JButton("Write Invoice");
    public final JButton jbtn_next = new JButton("Next");

    public final JTextArea jtxta_products = new JTextArea("List of Products e.g:\n116-456 x 2");
    public final JScrollPane jslp_productbought = new JScrollPane(jtxta_products);
    public final JComboBox<String> jcbx_allproducts = new JComboBox<>();

    public InvoiceEntry() {
        frame = this;
        initFrame();
    }

    public static void main(String[] args) {
        new InvoiceEntry();
        new Transaction();
    }

    //
    private void initFrame() {
        setTitle(frame_title);
        setSize(500, 600);
        setVisible(true);
        setResizable(false);
        setLocation(200, 200);
        setLayout(new GridLayout(19, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.cyan);
        getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.cyan));

        jtxta_products.setEditable(false);
        jtxta_products.setName("jtxta_productsbought");

        add(jl_name).setName("jl_name");
        add(jtf_name).setName("jtf_name");
        add(jl_address).setName("jl_address");
        add(jtf_address).setName("jtf_address");
        add(jl_city).setName("jl_city");
        add(jtf_city).setName("jtf_city");
        add(jl_description).setName("jl_description");
        add(jtf_description).setName("jtf_description");
        add(jl_province).setName("jl_province");
        add(jtf_province).setName("jtf_province");
        add(jl_zip).setName("jl_zip");
        add(jtf_zip).setName("jtf_zip");
        add(jl_productcode).setName("jl_productcode");
        add(jtf_productcode).setName("jtf_productcode");
        add(jl_invoicenumber).setName("jl_invoicenumber");
        add(jtf_invoicenumber).setName("jtf_invoicenumber");
        add(jl_customernumber).setName("jl_customernumber");
        add(jtf_customernumber).setName("jtf_customernumber");
        add(jl_productbought).setName("jl_productbought");
        add(jslp_productbought).setName("jslp_productbought");
        add(jl_quantity).setName("jl_quantity");
        add(jtf_quantity).setName("jtf_quantity");
        add(jl_payment).setName("jl_payment");
        add(jtf_payment).setName("jtf_payment");
        add(jl_price).setName("jl_price");
        add(jtf_price).setName("jtf_price");
        add(jl_deposit).setName("jl_deposit");
        add(jtf_deposit).setName("jtf_deposit");
        add(jl_allproducts).setName("jl_allproducts");
        add(jcbx_allproducts).setName("jcbx_allproducts");
        add(jbtn_addcustomer).setName("jbtn_addcustomer");
        add(jbtn_findproduct).setName("jbtn_findproduct");
        add(jbtn_listproduct).setName("jbtn_listproduct");
        add(jbtn_addinvoice).setName("jbtn_addinvoice");
        add(jbtn_showinvoice).setName("jbtn_showinvoice");
        add(jbtn_exit).setName("jbtn_exit");
        add(jbtn_writeinvoice).setName("jbtn_writeinvoice");
        add(jbtn_next).setName("jbtn_next");

        jbtn_next.addActionListener(this);
        jbtn_exit.addActionListener(this);
        jbtn_addinvoice.addActionListener(this);
        jbtn_addcustomer.addActionListener(this);
        jbtn_findproduct.addActionListener(this);
        jbtn_listproduct.addActionListener(this);
        jbtn_showinvoice.addActionListener(this);
        jbtn_writeinvoice.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        System.out.println(e.getActionCommand());
        if (source.equals(jbtn_next)) {
            Transaction.getTransactionFrame().setVisible(true);
        }
        if (source.equals(jbtn_exit)) {
            getTransactionFrame().dispose();
            Transaction.getTransactionFrame().dispose();
        }
        if (source.equals(jbtn_addinvoice)) {
            action.createInvoice(this);
        }
        if (source.equals(jbtn_addcustomer)) {
            action.addCustomer(this);
        }
        if (source.equals(jbtn_findproduct)) {
            action.findProduct(this);
        }
        if (source.equals(jbtn_listproduct)) {
            new Helpers().displayProductList(this);
        }
        if (source.equals(jbtn_showinvoice)) {
            action.showInvoice(this);
        }
        if (source.equals(jbtn_writeinvoice)) {
            action.printInvoice(this);
        }
    }

    public static InvoiceEntry getTransactionFrame() {
        return frame;
    }
}
