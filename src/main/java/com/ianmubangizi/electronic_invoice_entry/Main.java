/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ianmubangizi.electronic_invoice_entry;

import com.ianmubangizi.electronic_invoice_entry.Frames.InvoiceEntry;
import com.ianmubangizi.electronic_invoice_entry.Frames.Transaction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ian Mubangizi
 */
public class Main implements ActionListener {

    Transaction transaction;
    InvoiceEntry invoiceEntry;
    
    public void initMain(){
        this.invoiceEntry = new InvoiceEntry();
        this.transaction = new Transaction();

        this.invoiceEntry.jbtn_next.addActionListener(this);
        this.invoiceEntry.jbtn_addcustomer.addActionListener(this);
    }
        
    public static void main(String[] args) {
        Main app = new Main();
        app.initMain();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(invoiceEntry.jbtn_next)){
            transaction.setVisible(true);
        }
    }
}
