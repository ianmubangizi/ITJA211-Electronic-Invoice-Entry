package com.electronic_invoice.Services.Finders;

import com.electronic_invoice.Entities.Customer;
import static com.electronic_invoice.Services.DatabaseService.databaseService;
import com.electronic_invoice.Utils.IFindService;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * FindCustomer
 */
public class FindCustomer implements IFindService {

    private static FindCustomer service = null;

    private FindCustomer() {
        service = this;
    }

    /**
     *
     * @return
     */
    public static FindCustomer findCustomer() {
        if (service == null)
            new FindCustomer();
        return service;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean findId(int id) {
        ResultSet rs = databaseService()
                .getQuery(String.format("SELECT customer_number FROM orion.customer WHERE customer_number=%d;", id));
        try {
            if (rs.first()) {
                return true;
            }
        } catch (SQLException e) {

        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public int lastCreatedId() {
        ResultSet rs = databaseService().getQuery("SELECT customer_number FROM orion.customer ;");
        try {
            if (rs.last()) {
                return rs.getInt("customer_number");
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    /**
     *
     * @param sql
     * @return
     */
    public Customer withQuery(String sql) {
        ResultSet rs = databaseService().getQuery(sql);
        try {
            if (rs.isFirst()) {
                return new Customer(rs.getInt("customer_number"), rs.getString("name"), rs.getString("address"),
                        rs.getString("city"), rs.getString("province"), rs.getString("zip"), rs.getDouble("deposit"));
            }
        } catch (SQLException e) {

        }
        return new Customer();
    }

    /**
     *
     * @param id
     * @return
     */
    public int withInvoiceId(int id) {
        ResultSet rs = databaseService()
                .getQuery(String.format("SELECT customer_number FROM orion.invoice WHERE invoice_number=%d;", id));
        try {
            if (rs.next()) {
                return rs.getInt("customer_number");
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    /**
     *
     * @param id
     * @return
     */
    public Customer getCustomer(int id) {
        ResultSet rs = databaseService()
                .getQuery(String.format("SELECT * FROM orion.customer WHERE customer_number=%d;", id));
        try {
            if (rs.next()) {
                return new Customer(rs.getInt("customer_number"), rs.getString("name"), rs.getString("address"),
                        rs.getString("city"), rs.getString("province"), rs.getString("zip"), rs.getDouble("deposit"));
            }
        } catch (SQLException e) {

        }
        return new Customer();
    }
}
