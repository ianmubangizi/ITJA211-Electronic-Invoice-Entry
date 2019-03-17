/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice.Services;

import com.electronic_invoice.Entities.Customer;

//import java.sql.ResultSet;
/**
 * AddCustomer
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public final class AddCustomer {

    public AddCustomer(Customer c) {
        create(c);
    }

    public void create(Customer c) {
        new DatabaseService().updateQuery("INSERT INTO `orion`.`customer` ("
                + "`Name`, `Address`, `City`, `Province`, `Zip`, `Deposit`) "
                + "VALUES ('"
                + c.getName() + "','"
                + c.getAddress() + "','"
                + c.getCity() + "','"
                + c.getProvince() + "','"
                + c.getZip() + "',"
                + c.getDeposit() + ")");
    }
}
