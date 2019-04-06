package com.electronic_invoice.Services.Finders;

import com.electronic_invoice.Entities.Product;
import com.electronic_invoice.Utils.IFindService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.electronic_invoice.Services.DatabaseService.databaseService;

/**
 * FindProduct
 */
public class FindProduct implements IFindService {

    private static FindProduct service = null;

    private FindProduct() {
        service = this;
    }

    /**
     *
     * @return
     */
    public static FindProduct findProduct() {
        if (service == null) {
            new FindProduct();
        }
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
                .getQuery(String.format("SELECT * FROM orion.product WHERE product_code='%s';", id));
        try {
            if (rs != null && rs.next()) {
                return true;
            }
        } catch (SQLException ignored) {

        }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public int lastCreatedId() {
        return 0;
    }

    /**
     *
     * @param sql
     * @return
     */
    public Product withQuery(String sql) {
        return new Product();
    }

    /**
     *
     * @param id
     * @return Product
     */
    public Product withId(String id) {
        ResultSet rs = databaseService()
                .getQuery(String.format("SELECT * FROM orion.product WHERE product_code='%s';", id));
        try {
            if (rs != null && rs.next()) {
                return new Product(
                        rs.getString("product_code"),
                        rs.getString("description"),
                        rs.getInt("price"));
            }
        } catch (SQLException ignored) {

        }
        return new Product();
    }

    /**
     *
     * @return
     */
    public ArrayList<Product> allProducts() {
        ResultSet rs = databaseService().getQuery("SELECT * FROM orion.product;");
        try {
            ArrayList<Product> productList = new ArrayList<>();
            while (rs != null && rs.next()) {
                productList.add(
                        new Product(
                                rs.getString("product_code"),
                                rs.getString("description"),
                                rs.getInt("price")));
            }
            if (!productList.isEmpty()) {
                return productList;
            }
        } catch (SQLException ignored) {
        }
        return new ArrayList<>();
    }
}
