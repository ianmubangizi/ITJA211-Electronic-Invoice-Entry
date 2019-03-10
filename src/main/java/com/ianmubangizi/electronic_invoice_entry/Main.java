/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ianmubangizi.electronic_invoice_entry;

import com.ianmubangizi.electronic_invoice_entry.Entities.Customer;
import com.ianmubangizi.electronic_invoice_entry.Frames.InvoiceEntry;
import com.ianmubangizi.electronic_invoice_entry.Frames.Transaction;
import com.ianmubangizi.electronic_invoice_entry.Services.AddCustomer;

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
                new AddCustomer(new Customer(
                    ieFrame.jtf_customernumber.getText(),
                    ieFrame.jtf_name.getText(),
                    ieFrame.jtf_address.getText(),
                    ieFrame.jtf_city.getText(),
                    ieFrame.jtf_province.getText(),
                    ieFrame.jtf_zip.getText(),
                    ieFrame.jtf_deposit.getText()
                ));
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
