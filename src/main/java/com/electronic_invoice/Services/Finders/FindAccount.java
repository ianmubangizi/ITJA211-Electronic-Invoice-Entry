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
//                ResultSet rs = db.getQuery("SELECT * "
//                        + "FROM orion.account "
//                        + "WHERE customer_number=" + id + ";");
//                if (rs.first()) {
//                    return new Account(
//                            rs.getString("name"),
//                            rs.getInt("customer_number"),
//                            rs.getDouble("balance")
//                    );
//                }
            Customer customer = new FindCustomer().getCustomer(id);
            return new Account(
                    customer.getName(),
                    customer.getCustomer_number(),
                    customer.getDeposit());
        }
        return new Account();
    }
}
