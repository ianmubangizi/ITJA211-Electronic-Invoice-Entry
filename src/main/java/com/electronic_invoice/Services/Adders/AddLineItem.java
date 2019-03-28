/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice.Services.Adders;

import com.electronic_invoice.Entities.LineItem;
import com.electronic_invoice.Services.DatabaseService;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public final class AddLineItem {

    public void create(LineItem item) {
        new DatabaseService()
                .updateQuery(
                        "INSERT INTO `orion`.`lineitem` "
                        + "(`Invoice_Number`,`Product_Code`,`Quantity`) "
                        + "VALUES ("
                        + item.getInvoice_number() + ",'"
                        + item.getProduct_code() + "',"
                        + item.getQuantity() + ");"
                );
    }
}
