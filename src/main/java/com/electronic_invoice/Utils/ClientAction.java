package com.electronic_invoice.Utils;

import com.electronic_invoice.Entities.Customer;
import com.electronic_invoice.Entities.Invoice;
import com.electronic_invoice.Frames.InvoiceEntry;
import com.electronic_invoice.Services.AddCustomer;
import com.electronic_invoice.Services.AddInvoice;
import com.electronic_invoice.Services.FindCustomer;
import com.electronic_invoice.Services.FindInvoice;

/**
 * Action
 */
public class ClientAction {

    public void createCustomer(InvoiceEntry ieFrame) {

            new AddCustomer(
                    new Customer(ieFrame.jtf_name.getText(), ieFrame.jtf_address.getText(), ieFrame.jtf_city.getText(),
                            ieFrame.jtf_province.getText(), ieFrame.jtf_zip.getText(), ieFrame.jtf_deposit.getText()));
            displayCustomerNumber(ieFrame);
            //new MessagePane(null, "Error When Adding Customer [" + 1 + "]", "Adding Customer Error",
            //        EMessage.ERROR);
    }

    private void displayCustomerNumber(InvoiceEntry ieFrame){
        ieFrame.jtf_customernumber.setText("" + new FindCustomer().lastCreatedId() + "");
    }

    public void createInvoice(InvoiceEntry ieFrame) {
        new AddInvoice(new Invoice(ieFrame.jtf_invoicenumber.getText(), ieFrame.jtf_customernumber.getText(),
                ieFrame.jtf_payment.getText()));
        // displayInvoiceNumber(ieFrame);
    }

    private void displayInvoiceNumber(InvoiceEntry ieFrame) {
        ieFrame.jtf_invoicenumber.setText("" + new FindInvoice().lastCreatedId() + "");
    }
}