/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice;

import com.electronic_invoice.Entities.Customer;
import com.electronic_invoice.Entities.Invoice;
import com.electronic_invoice.Frames.InvoiceEntry;
import com.electronic_invoice.Frames.Transaction;
import com.electronic_invoice.Services.AddCustomer;
import com.electronic_invoice.Services.AddInvoice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *
 * @author Ian Mubangizi
 */
public class Main implements ActionListener {

    Transaction tsFrame;
    InvoiceEntry ieFrame;

    //
    public void initMain() {
        this.ieFrame = new InvoiceEntry();
        this.tsFrame = new Transaction();

        //
        this.ieFrame.jbtn_exit.addActionListener(this);
        this.ieFrame.jbtn_next.addActionListener(this);
        this.ieFrame.jbtn_addinvoice.addActionListener(this);
        this.ieFrame.jbtn_addcustomer.addActionListener(this);
        this.ieFrame.jbtn_findproduct.addActionListener(this);
        this.ieFrame.jbtn_listproduct.addActionListener(this);
        this.ieFrame.jbtn_showinvoice.addActionListener(this);
        this.ieFrame.jbtn_writeinvoice.addActionListener(this);

        //
        this.tsFrame.jbtn_check.addActionListener(this);
        this.tsFrame.jbtn_deposit.addActionListener(this);
        this.tsFrame.jbtn_calculate.addActionListener(this);
        this.tsFrame.jbtn_transaction.addActionListener(this);
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.initMain();
    }

    //
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(ieFrame.jbtn_next)) {
            tsFrame.setVisible(true);
        }
        if (source.equals(ieFrame.jbtn_addcustomer)) {
            try {
                new AddCustomer(
                    new Customer(
                        ieFrame.jtf_customernumber.getText(), 
                        ieFrame.jtf_name.getText(),
                        ieFrame.jtf_address.getText(), 
                        ieFrame.jtf_city.getText(),
                        ieFrame.jtf_province.getText(),
                        ieFrame.jtf_zip.getText(), 
                        ieFrame.jtf_deposit.getText()
                    )
                );
            } catch (ClassNotFoundException | SQLException e1) {
            }
        }
        if (source.equals(ieFrame.jbtn_addinvoice)) {
            new AddInvoice(new Invoice(
            // ieFrame.jtf
            // ieFrame.jtf
            // ieFrame.jtf
            ));
        }
    }
}
