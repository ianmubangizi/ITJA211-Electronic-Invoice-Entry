package com.electronic_invoice.Services.Adders;

import com.electronic_invoice.Entities.Invoice;
import com.electronic_invoice.Utils.ECallTypes;
import com.electronic_invoice.Utils.Helpers;
import com.electronic_invoice.Utils.ICustomerNotFound;

import static com.electronic_invoice.Services.DatabaseService.databaseService;
import static com.electronic_invoice.Services.Finders.FindCustomer.findCustomer;

/**
 * AddInvoice
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public final class AddInvoice implements ICustomerNotFound {

    private static AddInvoice service = null;

    private AddInvoice() {
        service = this;
    }

    /**
     * @return
     */
    public static AddInvoice invoiceService() {
        if (service == null)
            new AddInvoice();
        return service;
    }

    /**
     * @param invoice
     */
    public void create(Invoice invoice) {
        databaseService().updateQuery(
                String.format("INSERT INTO `orion`.`invoice` (`Customer_Number`,`Payment`) VALUES (%s,%f);",
                        (findCustomer().findId(invoice.getCustomer_number()) ? invoice.getCustomer_number()
                                : getCreatedId(invoice.getCustomer_number())),
                        (invoice.getPayment())));
    }

    /**
     * @param columnName
     * @param value
     * @param key
     */
    public void update(String columnName, String value, int key) {
        databaseService().updateQuery(String.format(
                "UPDATE `orion`.`invoice` SET `invoice`.`%s`=%s WHERE `invoice_number`=%s", columnName, value, key));
    }

    /**
     * @param t
     * @deprecated
     */
    @Override
    @Deprecated
    public void createNewCustomer(ECallTypes t) {
        switch (t) {
            case NEED_VAILD_CUSTOMERID:
                new Helpers().addCustomer(ECallTypes.NEED_VAILD_CUSTOMERID);
                break;
            case ADD_INVOICE_CUSTOMER:
                new Helpers().addCustomer(ECallTypes.ADD_INVOICE_CUSTOMER);
                break;
            default:
                break;
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    @Deprecated
    public int getCreatedId(int id) {
        if ((Integer.toString(id).matches("(^(?!0*(\\.0+)?$)(\\d{1,200})$)"))) {
            createNewCustomer(ECallTypes.ADD_INVOICE_CUSTOMER);
            return id;
        }
        createNewCustomer(ECallTypes.NEED_VAILD_CUSTOMERID);
        return 0;
    }

}
