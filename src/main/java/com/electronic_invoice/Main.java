/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice;

import com.electronic_invoice.Frames.InvoiceEntry;
import com.electronic_invoice.Frames.Transaction;
import com.electronic_invoice.Utils.ClientAction;
import com.electronic_invoice.Utils.ECallTypes;
import com.electronic_invoice.Utils.Generate;
import com.electronic_invoice.Utils.MessagePane;
import com.electronic_invoice.Utils.MessagePane.EMessage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class Main implements ActionListener {

    public Transaction tsFrame;
    public InvoiceEntry ieFrame;
    public MessagePane message_pane;

    //
    public void initMain() {
        this.ieFrame = new InvoiceEntry();
        this.tsFrame = new Transaction();
        this.ieFrame.jbtn_exit.addActionListener(this);
        this.ieFrame.jbtn_next.addActionListener(this);
        this.ieFrame.jbtn_addinvoice.addActionListener(this);
        this.ieFrame.jbtn_addcustomer.addActionListener(this);
        this.ieFrame.jbtn_findproduct.addActionListener(this);
        this.ieFrame.jbtn_listproduct.addActionListener(this);
        this.ieFrame.jbtn_showinvoice.addActionListener(this);
        this.ieFrame.jbtn_writeinvoice.addActionListener(this);
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
        if (source.equals(ieFrame.jbtn_showinvoice)) {
            showInvoice_btn_action();
        }
        if (source.equals(ieFrame.jbtn_writeinvoice)) {
            printInvoive_btn_action();
        }
    }

    //
    public void addCustomer_btn_action(ECallTypes t) {
        switch (t) {
            case ADD_CUSTOMER:
                new ClientAction().createCustomer(this.ieFrame);
                break;
            case CUSTOMERID_TAKEN:
                this.message_pane = new MessagePane(null,
                        "The ", "Adding Customer Error", EMessage.ERROR);
                break;
            case NEED_VAILD_CUSTOMERID:
                this.message_pane = new MessagePane(null,
                        "Can't Create Invoice without a vaild Customer Number\n "
                        + "Press Okay and Enter Customer Info to get a Number\n"
                        + "Click the Add Customer Button after inputting Customer Info",
                        "Adding Invoice Error", EMessage.ERROR);
                break;
            case ADD_INVOICE_CUSTOMER:
                this.message_pane = new MessagePane(null,
                        "Enter your Customer Details then\n"
                        + "Click the Add Customer Button then\n"
                        + "Try Adding an Invoice",
                        "Need a Customer Number - When Adding Invoice", EMessage.INFO);
                ieFrame.jtf_customernumber.setText(String.valueOf(new Generate().nextCustomerNumber()));
                break;
            default:
                break;
        }

    }

    public void addInvoice_btn_action() {
        new ClientAction().createInvoice(this.ieFrame);
    }

    public void showInvoice_btn_action() {
        new ClientAction().showInvoice(this.ieFrame);
    }

    public void printInvoive_btn_action() {
        new ClientAction().printInvoice(this.ieFrame);
    }
}
