/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice.Services.Finders;

import com.electronic_invoice.Entities.Account;
import com.electronic_invoice.Entities.Customer;
import com.electronic_invoice.Services.DatabaseService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class FindAccount {

    DatabaseService db = new DatabaseService();

    public Account getById(int id) {
        if (new FindCustomer().byId(id)) {
            try {
                ResultSet rs = db.getQuery("SELECT * "
                        + "FROM orion.account "
                        + "WHERE customer_number=" + id + ";");
                if (rs.first()) {
                    return new Account(
                            rs.getString("name"),
                            rs.getInt("customer_number"),
                            rs.getDouble("balance")
                    );
                } else {
                    Customer customer = new FindCustomer().getCustomer(id);
                    Account account = new Account(
                            customer.getName(),
                            customer.getCustomer_number(),
                            customer.getDeposit());
                    return account;
                }
            } catch (SQLException ex) {
                Logger.getLogger(FindAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new Account();
    }
}
