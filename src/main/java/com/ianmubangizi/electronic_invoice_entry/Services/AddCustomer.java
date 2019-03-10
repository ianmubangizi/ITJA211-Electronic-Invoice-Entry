/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ianmubangizi.electronic_invoice_entry.Services;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ianmubangizi.electronic_invoice_entry.Entities.Customer;

/**
 * AddCustomer
 * 
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class AddCustomer {
    DatabaseService db = new DatabaseService();

    public AddCustomer(Customer c) throws ClassNotFoundException, SQLException {

        db.updateQuery(
            
            "INSERT INTO `orion`.`customer` (" +
                        "`Customer_Number`, `Name`, `Address`, `City`, `Province`, `Zip`, `Deposit`) VALUES ("
                        + (genCustom_number(c.getCustomer_number()))
                        + ",'" + c.getName()
                        + "','" + c.getAddress()
                        + "','" + c.getCity()
                        + "','" + c.getProvince()
                        + "','" + c.getZip()
                        + "'," + c.getDepoist()
                        + ")"
        );
    }
    public String genCustom_number(int c) throws SQLException {
        ResultSet rs = db.getQuery("SELECT `Customer_Number` FROM `orion`.`customer` WHERE `Customer_Number`="+ c +";");
        while(rs.next()){
            return (Integer.toString(c).matches("\\d{1,200}[^.\\s]") || c == rs.getInt("Customer_Number")  ? "35142" : "1");
        }
        return "";
    }
}
