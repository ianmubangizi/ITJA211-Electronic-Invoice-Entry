package com.electronic_invoice.Services;

import com.electronic_invoice.Main;
import com.electronic_invoice.Entities.Invoice;
import com.electronic_invoice.Utils.ECallTypes;
import com.electronic_invoice.Utils.ICustomerNotFound;

/**
 * AddInvoice
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class AddInvoice implements ICustomerNotFound {
    DatabaseService db = new DatabaseService();

    public AddInvoice(Invoice invoice){
        create(invoice);
    }

    public void create(Invoice invoice){
            db.setDbService();
            db.updateQuery("INSERT INTO `orion`.`invoice` (`Customer_Number`,`Payment`) VALUES ("
            + (new FindCustomer().byId(invoice.getCustomer_number()) ? invoice.getCustomer_number()
                    : getCreatedId(new FindCustomer().lastCreatedId()))
            + "," + invoice.getPayment() + ");");
    }

    @Override
    public void createNewCustomer() {
        new Main().addCustomer_btn_action(ECallTypes.NO_CUSTOMER);
        new Main().addCustomer_btn_action(ECallTypes.ADD_INVOICE_CUSTOMER);
    }

    @Override
    public int getCreatedId(int id) {
        //     if (!new FindCustomer().byId(id))
        //         createNewCustomer();
        return id;
    }

}