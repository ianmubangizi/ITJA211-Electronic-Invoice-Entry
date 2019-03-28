/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice.Services.Finders;

import com.electronic_invoice.Entities.Account;
import com.electronic_invoice.Entities.Customer;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class FindAccount {

    public Account getById(int id) {
        if (new FindCustomer().byId(id)) {
            Customer customer = new FindCustomer().getCustomer(id);
            return new Account(
                    customer.getName(),
                    customer.getCustomer_number(),
                    customer.getDeposit());
        }
        return new Account();
    }
}
