package com.electronic_invoice.Services;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.electronic_invoice.Utils.IFindService;

/**
 * FindInvoice
 */
public class FindInvoice implements IFindService {

    DatabaseService db = new DatabaseService();

    public FindInvoice() {
        db.setDbService();
    }

    @Override
    public boolean byId(int id) {
        ResultSet rs = db.getQuery("SELECT invoice_number " + "FROM orion.invoice " + "WHERE invoice_number=" + id + ";");
        try {
            if (rs.isFirst()) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    @Override
    public int lastCreatedId() {
        ResultSet rs = db.getQuery("SELECT invoice_number " + "FROM orion.invoice ;");
        try {
            if (rs.last()) {

                return rs.getInt("invoice_number");

            }
        } catch (SQLException e) {

        }
        return 0;
    }

    @Override
    public int findByQuery(String sql) {
        ResultSet rs = db.getQuery(sql);
        try {
            if (rs.isFirst()) {
                return 1;
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    
}