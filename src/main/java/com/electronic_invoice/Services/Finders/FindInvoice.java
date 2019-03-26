package com.electronic_invoice.Services.Finders;

import com.electronic_invoice.Entities.Invoice;
import com.electronic_invoice.Services.DatabaseService;
import com.electronic_invoice.Utils.IFindService;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * FindInvoice
 */
public class FindInvoice implements IFindService {

    DatabaseService db = new DatabaseService();

    @Override
    public boolean byId(int id) {
        ResultSet rs = db
                .getQuery("SELECT invoice_number " + "FROM orion.invoice "
                        + "WHERE invoice_number=" + id + ";");
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
        ResultSet rs = db.getQuery("SELECT invoice_number "
                + "FROM orion.invoice ;");
        try {
            if (rs.last()) {

                return rs.getInt("invoice_number");

            }
        } catch (SQLException e) {

        }
        return 0;
    }

    @Override
    public int withQuery(String sql) {
        ResultSet rs = db.getQuery(sql);
        try {
            while (rs.next()) {
                if (rs.isLast()) {
                    return rs.getInt("invoice_number");
                }
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    public int withInvoiceId(int id) {
        ResultSet rs = db
                .getQuery("SELECT invoice_number " + "FROM orion.invoice "
                        + "WHERE invoice_number=" + id + ";");
        try {
            if (rs.next()) {
                return rs.getInt("invoice_number");
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    public Invoice getInvoice(int id) {
        ResultSet rs = db.getQuery("SELECT * " + "FROM orion.invoice "
                + "WHERE customer_number=" + id + ";");
        try {
            if (rs.first()) {
                return new Invoice(rs.getInt("invoice_number"),
                        rs.getInt("customer_number"), rs.getDouble("payment"));
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
