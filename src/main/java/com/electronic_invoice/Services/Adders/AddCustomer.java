package com.electronic_invoice.Services.Adders;

import com.electronic_invoice.Entities.Customer;
import static com.electronic_invoice.Services.DatabaseService.databaseService;

/**
 * customerService
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public final class AddCustomer {

    private static AddCustomer service = null;

    private AddCustomer() {
        service = this;
    }

    /**
     *
     * @return
     */
    public static AddCustomer customerService() {
        if (service == null) {
            new AddCustomer();
        }
        return service;
    }

    /**
     *
     * @param customer
     */
    public void create(Customer customer) {
        databaseService().updateQuery(String.format(
                "INSERT INTO `orion`.`customer` (`Name`, `Address`, `City`, `Province`, `Zip`, `Deposit`) VALUES ('%s','%s','%s','%s','%s',%f)",
                customer.getName(), customer.getAddress(), customer.getCity(), customer.getProvince(),
                customer.getZip(), customer.getDeposit())
        );
    }

    //[Todo] Use Customer and String[] 

    /**
     *
     * @param columName
     * @param value
     * @param key
     */
    public void update(String columName, String value, int key) {
        databaseService().updateQuery(String.format(
                "UPDATE `orion`.`customer` SET `customer`.`%s`=%s WHERE `customer_number`=%s",
                columName, value, key)
        );
    }
}
