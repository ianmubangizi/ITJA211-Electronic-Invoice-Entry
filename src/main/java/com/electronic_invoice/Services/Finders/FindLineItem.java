package com.electronic_invoice.Services.Finders;

import com.electronic_invoice.Entities.LineItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.electronic_invoice.Services.DatabaseService.databaseService;

/**
 * FindLineItem
 */
public class FindLineItem {

    private static FindLineItem service = null;

    private FindLineItem() {
        service = this;
    }

    /**
     * @return
     */
    public static FindLineItem findLineItem() {
        if (service == null)
            new FindLineItem();
        return service;
    }

    /**
     * @param id
     * @return
     */
    public ArrayList<LineItem> withInvoiceId(int id) {
        ResultSet rs = databaseService()
                .getQuery(String.format("SELECT * FROM orion.lineitem WHERE invoice_number=%s;", id));
        try {
            ArrayList<LineItem> productItemList = new ArrayList<>();
            if (rs != null) {
                while (rs.next()) {
                    productItemList.add(
                            new LineItem(
                                    rs.getInt("invoice_number"),
                                    rs.getString("product_code"),
                                    rs.getInt("quantity")));
                }
            }
            if (!productItemList.isEmpty()) {
                return productItemList;
            }
        } catch (SQLException ignored) {

        }
        return new ArrayList<>();
    }
}
