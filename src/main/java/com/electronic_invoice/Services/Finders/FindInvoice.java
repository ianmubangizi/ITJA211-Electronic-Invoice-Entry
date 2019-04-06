package com.electronic_invoice.Services.Finders;

import com.electronic_invoice.Entities.Invoice;
import com.electronic_invoice.Utils.IFindService;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.electronic_invoice.Services.DatabaseService.databaseService;

/**
 * FindInvoice
 */
public class FindInvoice implements IFindService {

    private static FindInvoice service = null;

    private FindInvoice() {
        service = this;
    }


    /**
     * @return
     */
    public static FindInvoice findInvoice() {
        if (service == null)
            new FindInvoice();
        return service;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean findId(int id) {
        ResultSet rs = databaseService()
                .getQuery(String.format("SELECT invoice_number FROM orion.invoice WHERE invoice_number=%s;", id));
        try {
            if (rs != null && rs.isFirst()) {
                return true;
            }
        } catch (SQLException ignored) {

        }
        return false;
    }

    /**
     * @return
     */
    @Override
    public int lastCreatedId() {
        ResultSet rs = databaseService().getQuery("SELECT invoice_number FROM orion.invoice;");
        try {
            if (rs != null && rs.last()) {
                return rs.getInt("invoice_number");
            }
        } catch (SQLException ignored) {

        }
        return 0;
    }

    /**
     * @param sql
     * @return
     */
    public int withQuery(String sql) {
        ResultSet rs = databaseService().getQuery(sql);
        try {
            if (rs != null) {
                while (rs.next()) {
                    if (rs.isLast()) {
                        return rs.getInt("invoice_number");
                    }
                }
            }
        } catch (SQLException ignored) {

        }
        return 0;
    }

    /**
     * @param id
     * @return
     */
    public int withInvoiceId(int id) {
        ResultSet rs = databaseService().getQuery(
                String.format("SELECT invoice_number FROM orion`.`invoice WHERE invoice_number=%s;", id));
        try {
            if (rs != null && rs.next()) {
                return rs.getInt("invoice_number");
            }
        } catch (SQLException ignored) {

        }
        return 0;
    }

    /**
     * @param id
     * @return
     */
    public Invoice getInvoice(int id) {
        ResultSet rs = databaseService()
                .getQuery(String.format("SELECT * FROM orion.invoice WHERE customer_number=%s;", id));
        try {
            if (rs != null && rs.last()) {
                return new Invoice(
                        rs.getInt("invoice_number"),
                        rs.getInt("customer_number"),
                        rs.getDouble("payment"));
            }
        } catch (SQLException ignored) {
        }
        return null;
    }
}
