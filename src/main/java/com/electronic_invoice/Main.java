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
import com.electronic_invoice.Utils.Helpers;
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
    private final ClientAction action = new ClientAction();

    //
    public void initMain() {
        this.tsFrame = new Transaction();
        this.ieFrame = new InvoiceEntry();
        //
        this.ieFrame.jbtn_next.addActionListener(this);
        this.ieFrame.jbtn_exit.addActionListener(this);
        this.tsFrame.jbtn_check.addActionListener(this);
        this.tsFrame.jbtn_deposit.addActionListener(this);
        this.tsFrame.jbtn_calculate.addActionListener(this);
        this.ieFrame.jbtn_addinvoice.addActionListener(this);
        this.tsFrame.jbtn_transaction.addActionListener(this);
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
            action.createInvoice(this.ieFrame);
        }
        if (source.equals(ieFrame.jbtn_showinvoice)) {
            action.showInvoice(this.ieFrame);
        }
        if (source.equals(ieFrame.jbtn_writeinvoice)) {
            action.printInvoice(this.ieFrame);
        }
        if (source.equals(ieFrame.jbtn_listproduct)) {
            new Helpers().displayProductList(this.ieFrame);
        }
        if (source.equals(ieFrame.jbtn_findproduct)) {
            action.findProduct(this.ieFrame);
        }
        if (source.equals(ieFrame.jbtn_exit)) {
            ieFrame.dispose();
        }
        if(source.equals(tsFrame.jbtn_check)){
            action.checkAccount(tsFrame, ieFrame);
        }
        if(source.equals(tsFrame.jbtn_deposit)){
            action.addAccount(tsFrame);
        }        
        if(source.equals(tsFrame.jbtn_calculate)){
            action.calcuteAndDeposit();//(tsFrame);
        }
    }

    //
    public void addCustomer_btn_action(ECallTypes t) {
        switch (t) {
            case ADD_CUSTOMER:
                action.createCustomer(this.ieFrame);
                break;
            case CUSTOMERID_TAKEN:
                this.message_pane = new MessagePane(null,
                        "The ", "Adding Customer Error", EMessage.ERROR);
                break;
            case NEED_VAILD_CUSTOMERID:
                this.message_pane = new MessagePane(null,
                        "Can't Create Invoice without a vaild Customer Number\n"
                        + "Press Okay and Enter Customer Info to get a Number\n"
                        + "Click the Add Customer Button after inputting Customer Info",
                        "Adding Invoice Error", EMessage.ERROR);
                break;
            case ADD_INVOICE_CUSTOMER:
                this.message_pane = new MessagePane(null,
                        "Enter your Customer Details then\n"
                        + "Click the Add Customer Button then\n"
                        + "Try Adding an Invoice",
                        "Need a Customer Number - When Adding Invoice",
                        EMessage.INFO);
                break;
            default:
                break;
        }
    }
}
