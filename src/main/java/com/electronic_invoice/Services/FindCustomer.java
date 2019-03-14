package com.electronic_invoice.Services;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.electronic_invoice.Utils.IFindService;

/**
 * FindCustomer
 */
public class FindCustomer implements IFindService{
    DatabaseService db = new DatabaseService();

    public FindCustomer() {
        db.setDbService();
    }

    @Override
    public boolean byId(int id) {
        ResultSet rs = db.getQuery("SELECT customer_number " + "FROM orion.customer " + "WHERE customer_number=" + id + ";");
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
        ResultSet rs = db.getQuery("SELECT customer_number " + "FROM orion.customer ;");
        try {
            if (rs.last()) {

                return rs.getInt("customer_number");

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