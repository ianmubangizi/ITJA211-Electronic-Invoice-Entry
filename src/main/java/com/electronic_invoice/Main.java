/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.electronic_invoice.Frames.InvoiceEntry;
import com.electronic_invoice.Frames.Transaction;
import com.electronic_invoice.Utils.ClientAction;
import com.electronic_invoice.Utils.ECallTypes;
import com.electronic_invoice.Utils.Generate;
import com.electronic_invoice.Utils.MessagePane;
import com.electronic_invoice.Utils.MessagePane.EMessage;

/**
 *
 * @author Ian Mubangizi
 */
public class Main implements ActionListener {

    Transaction tsFrame;
    InvoiceEntry ieFrame;
    MessagePane message_pane;

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
            addCustomer_btn_action(ECallTypes.ADD_CUSTOMER);
        }
        if (source.equals(ieFrame.jbtn_addinvoice)) {
            addInvoice_btn_action();
        }
    }

    public void addCustomer_btn_action(ECallTypes t) {
        switch (t) {
        case NO_CUSTOMER:
            this.message_pane = new MessagePane(null,
                    "Error, Can't Create Invoice without a Vaild Customer Number\n "
                            + "Press Okay and Enter Customer Info to get a Number\n"
                            + "Click the Add Customer Button after filling in Customer Info",
                    "Adding Invoice Error", EMessage.ERROR);
            break;
        case ADD_CUSTOMER:
            new ClientAction().createCustomer(this.ieFrame);
            break;
        case ADD_INVOICE_CUSTOMER:
            if (this.message_pane == null) {
                this.message_pane = new MessagePane(null,
                        "Enter [" + new Generate().nextCustomerNumber() + "] as your new Customer Number\n"
                                + "Click the Add Customer Button then\n" + "Click the Add Invoice Button",
                        "New Customer Number", EMessage.INFO);
            }
            break;
        default:
            break;
        }

    }

    public void addInvoice_btn_action() {
        new ClientAction().createInvoice(this.ieFrame);
    }

}
