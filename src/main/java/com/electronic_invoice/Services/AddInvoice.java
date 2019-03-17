package com.electronic_invoice.Services;

import com.electronic_invoice.Entities.Invoice;
import com.electronic_invoice.Main;
import com.electronic_invoice.Utils.ECallTypes;
import com.electronic_invoice.Utils.ICustomerNotFound;

/**
 * AddInvoice
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public final class AddInvoice implements ICustomerNotFound {

    public AddInvoice(Invoice invoice) {
        create(invoice);
    }

    public void create(Invoice invoice) {
        new DatabaseService().updateQuery(
                "INSERT INTO `orion`.`invoice` (`Customer_Number`,`Payment`) "
                + "VALUES ("
                + (new FindCustomer().byId(invoice.getCustomer_number())
                ? invoice.getCustomer_number()
                : getCreatedId(invoice.getCustomer_number()))
                + "," + (invoice.getPayment()) + ");");
    }

    @Override
    public void createNewCustomer(ECallTypes t) {

        switch (t) {
            case NEED_VAILD_CUSTOMERID:
                new Main().addCustomer_btn_action(ECallTypes.NEED_VAILD_CUSTOMERID);
                break;
            case ADD_INVOICE_CUSTOMER:
                new Main().addCustomer_btn_action(ECallTypes.ADD_INVOICE_CUSTOMER);
                break;
            default:
                break;
        }
    }

    @Override
    public int getCreatedId(int id) {
        if ((Integer.toString(id).matches("(^(?!0*(\\.0+)?$)(\\d{1,200})$)"))) {
            createNewCustomer(ECallTypes.ADD_INVOICE_CUSTOMER);
            return id;
        }
        createNewCustomer(ECallTypes.NEED_VAILD_CUSTOMERID);
        return 0;
    }

}
