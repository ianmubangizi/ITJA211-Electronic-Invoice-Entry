package com.electronic_invoice.Services.Finders;

import com.electronic_invoice.Entities.LineItem;
import com.electronic_invoice.Services.DatabaseService;
import com.electronic_invoice.Utils.IFindService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * FindLineItem
 */
public class FindLineItem implements IFindService {

    DatabaseService db = new DatabaseService();

    @Override
    public boolean byId(int id) {
        return false;
    }

    @Override
    public int lastCreatedId() {
        return 0;
    }

    @Override
    public int withQuery(String sql) {
        return 0;
    }

    public ArrayList<LineItem> withInvoiceId(int id) {
        ResultSet rs = db.getQuery("SELECT * FROM orion.lineitem " + "WHERE invoice_number=" + id + ";");
        try {
            ArrayList<LineItem> producItemList = new ArrayList<>();
            while (rs.next()) {
                producItemList.add(
                        new LineItem(rs.getInt("invoice_number"), rs.getString("product_code"), rs.getInt("quantity")));
            }
            if (!producItemList.isEmpty()) {
                return producItemList;
            }
        } catch (SQLException e) {

        }
        return new ArrayList<>();
    }
}
