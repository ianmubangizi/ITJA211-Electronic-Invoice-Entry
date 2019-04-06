package com.electronic_invoice.Services.Finders;

import com.electronic_invoice.Entities.Account;
import com.electronic_invoice.Entities.Customer;

import static com.electronic_invoice.Services.Finders.FindCustomer.findCustomer;

/**
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class FindAccount {

    private static FindAccount service = null;

    private FindAccount() {
        service = this;
    }

    /**
     * @return
     */
    public static FindAccount findAccount() {
        if (service == null)
            new FindAccount();
        return service;
    }

    /**
     * @param id
     * @return
     */
    public Account getById(int id) {
        if (findCustomer().findId(id)) {
            Customer customer = findCustomer().getCustomer(id);
            return new Account(customer.getName(), customer.getCustomer_number(), customer.getDeposit());
        }
        return new Account();
    }
}
