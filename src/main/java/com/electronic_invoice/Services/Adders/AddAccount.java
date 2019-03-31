package com.electronic_invoice.Services.Adders;

import com.electronic_invoice.Entities.Account;
import static com.electronic_invoice.Services.DatabaseService.databaseService;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public final class AddAccount {

    private static AddAccount service = null;

    private AddAccount() {
        service = this;
    }

    /**
     *
     * @return
     */
    public static AddAccount accountService() {
        if (service == null)
            new AddAccount();
        return service;
    }

    /**
     *
     * @param account
     */
    public void create(Account account) {
        databaseService().updateQuery(
                String.format("INSERT INTO `orion`.`account` (`name`,`customer_number`,`balance`) VALUES ('%s',%d,%f);",
                        account.getName(), account.getCustomer_number(), account.getBalance()));
    }

    /**
     *
     * @param columnName
     * @param value
     * @param key
     */
    public void update(String columnName, String value, int key) {
        databaseService().updateQuery(String.format(
                "UPDATE `orion`.`account` SET `account`.`%s`=%s WHERE `account`.`customer_number`=%d;", columnName, value, key));
    }
}
