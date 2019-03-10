/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ianmubangizi.electronic_invoice_entry.Frames;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Ian Mubangizi
 */
public class InvoiceEntry extends JFrame {

    private static final long serialVersionUID = 2720710036607580794L;

    public String frame_title = "Electronic Invoice Entry";

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
    private final JTextField jtf_name = new JTextField();
    private final JTextField jtf_address = new JTextField();
    private final JTextField jtf_city = new JTextField();
    private final JTextField jtf_description = new JTextField();
    private final JTextField jtf_province = new JTextField();
    private final JTextField jtf_zip = new JTextField();
    private final JTextField jtf_productcode = new JTextField();
    private final JTextField jtf_invoicenumber = new JTextField();
    private final JTextField jtf_customernumber = new JTextField();
    private final JTextField jtf_quantity = new JTextField();
    private final JTextField jtf_payment = new JTextField();
    private final JTextField jtf_price = new JTextField();
    private final JTextField jtf_deposit = new JTextField();

    //
    public final JButton jbtn_addcustomer = new JButton("Add Customer");
    public final JButton jbtn_findproduct = new JButton("Find Product");
    public final JButton jbtn_listproduct = new JButton("List Product");
    public final JButton jbtn_addinvoice = new JButton("Add Invoice");
    public final JButton jbtn_showinvoice = new JButton("Show Invoice");
    public final JButton jbtn_exit = new JButton("Exit");
    public final JButton jbtn_writeinvoice = new JButton("Write Invoice");
    public final JButton jbtn_next = new JButton("Next");

    private final JScrollPane jslp_productbought = new JScrollPane();
    private final JComboBox<InvoiceEntry> jcbx_allproducts = new JComboBox<>();

    public InvoiceEntry() throws HeadlessException {
        initFrame();
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

        add(jl_name);
        add(jtf_name);
        add(jl_address);
        add(jtf_address);
        add(jl_city);
        add(jtf_city);
        add(jl_description);
        add(jtf_description);
        add(jl_province);
        add(jtf_province);
        add(jl_zip);
        add(jtf_zip);
        add(jl_productcode);
        add(jtf_productcode);
        add(jl_invoicenumber);
        add(jtf_invoicenumber);
        add(jl_customernumber);
        add(jtf_customernumber);
        add(jl_productbought);
        add(jslp_productbought);
        add(jl_quantity);
        add(jtf_quantity);
        add(jl_payment);
        add(jtf_payment);
        add(jl_price);
        add(jtf_price);
        add(jl_deposit);
        add(jtf_deposit);
        add(jl_allproducts);
        add(jcbx_allproducts);
        add(jbtn_addcustomer);
        add(jbtn_findproduct);
        add(jbtn_listproduct);
        add(jbtn_addinvoice);
        add(jbtn_showinvoice);
        add(jbtn_exit);
        add(jbtn_writeinvoice);
        add(jbtn_next);
    }
}
