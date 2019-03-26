package com.electronic_invoice.Services.Finders;

import com.electronic_invoice.Entities.Product;
import com.electronic_invoice.Services.DatabaseService;
import com.electronic_invoice.Utils.IFindService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * FindProduct
 */
public class FindProduct implements IFindService {

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

    /**
     * 
     * @param id
     * @return Product
     */
    public Product withProductId(String id) {
        ResultSet rs = db.getQuery("SELECT * " + "FROM orion.product " + "WHERE product_code=" + id + ";");
        try {
            if (rs.next()) {
                return new Product(rs.getString("product_code"), rs.getString("description"), rs.getInt("price"));
            }
        } catch (SQLException e) {

        }
        return new Product();
    }

    public ArrayList<Product> allProducts() {
        ResultSet rs = db.getQuery("SELECT * FROM orion.product;");
        try {
            ArrayList<Product> producList = new ArrayList<>();
            while (rs.next()) {
                producList.add(
                        new Product(rs.getString("product_code"), rs.getString("description"), rs.getInt("price")));
            }
            if (!producList.isEmpty()) {
                return producList;
            }
        } catch (SQLException e) {
        }
        return new ArrayList<>();
    }
}