/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice.Services.Adders;

//import java.sql.ResultSet;

import com.electronic_invoice.Entities.Customer;
import com.electronic_invoice.Services.DatabaseService;

/**
 * AddCustomer
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public final class AddCustomer {

    public void create(Customer customer) {
        new DatabaseService().updateQuery("INSERT INTO `orion`.`customer` ("
                + "`Name`, `Address`, `City`, `Province`, `Zip`, `Deposit`) "
                + "VALUES ('"
                + customer.getName() + "','"
                + customer.getAddress() + "','"
                + customer.getCity() + "','"
                + customer.getProvince() + "','"
                + customer.getZip() + "',"
                + customer.getDeposit() + ")");
    }
}
