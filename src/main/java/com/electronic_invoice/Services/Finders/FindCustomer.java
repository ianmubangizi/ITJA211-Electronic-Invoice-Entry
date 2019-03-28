package com.electronic_invoice.Services.Finders;

import com.electronic_invoice.Entities.Customer;
import com.electronic_invoice.Services.DatabaseService;
import com.electronic_invoice.Utils.IFindService;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * FindCustomer
 */
public class FindCustomer implements IFindService {

    DatabaseService db = new DatabaseService();

    @Override
    public boolean byId(int id) {
        ResultSet rs = db
                .getQuery("SELECT customer_number " + "FROM orion.customer " + "WHERE customer_number=" + id + ";");
        try {
            if (rs.first()) {
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
    public int withQuery(String sql) {
        ResultSet rs = db.getQuery(sql);
        try {
            if (rs.isFirst()) {
                return 1;
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    public int withInvoiceId(int id) {
        ResultSet rs = db
                .getQuery("SELECT customer_number " + "FROM orion.invoice " + "WHERE invoice_number=" + id + ";");
        try {
            if (rs.next()) {
                return rs.getInt("customer_number");
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    public Customer getCustomer(int id) {
        ResultSet rs = db.getQuery("SELECT * FROM orion.customer " + "WHERE customer_number=" + id + ";");
        try {
            if (rs.next()) {
                return new Customer(
                    rs.getInt("customer_number"), 
                    rs.getString("name"), 
                    rs.getString("address"),
                    rs.getString("city"), 
                    rs.getString("province"), 
                    rs.getString("zip"), 
                    rs.getDouble("deposit")
                );
            }
        } catch (SQLException e) {
            
        }
        return new Customer();
    }
}
