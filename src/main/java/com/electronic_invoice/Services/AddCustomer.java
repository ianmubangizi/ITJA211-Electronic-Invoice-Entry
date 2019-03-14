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
public class AddCustomer {
    DatabaseService db = new DatabaseService();

    public AddCustomer(Customer c) {
        create(c);
    }

    public void create(Customer c) {
        db.setDbService();
        db.updateQuery("INSERT INTO `orion`.`customer` ("
                + "`Name`, `Address`, `City`, `Province`, `Zip`, `Deposit`) VALUES ('" + c.getName() + "','"
                + c.getAddress() + "','" + c.getCity() + "','" + c.getProvince() + "','" + c.getZip() + "',"
                + c.getDeposit() + ")");
    }

    // private String newCustomerNumber(int c) throws SQLException {
    // ResultSet rs = db.getQuery(
    // "SELECT `Customer_Number` "
    // + "FROM `orion`.`customer` "
    // + "WHERE `Customer_Number`="+ c +";"
    // );
    // while(rs.next()){
    // return (Integer.toString(c).matches("\\d{1,200}[^.\\s]") ||
    // c == rs.getInt("Customer_Number") ? "35142" : "1");
    // }
    // return "";
    // }

}
