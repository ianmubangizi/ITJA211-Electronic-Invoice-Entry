/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice.Services.Adders;

import com.electronic_invoice.Entities.Account;
import com.electronic_invoice.Services.DatabaseService;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public final class AddAccount {
    
    public void create(Account account) {
        new DatabaseService()
                .updateQuery(
                        "INSERT INTO `orion`.`account` "
                        + "(`name`,`customer_number`,`balance`) "
                        + "VALUES (\"" + account.getName()
                        + "\"," + account.getCustomer_number()
                        + "," + account.getBalance()
                        + ");"
                );
    }
}
