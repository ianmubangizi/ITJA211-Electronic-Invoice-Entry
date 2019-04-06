package com.electronic_invoice.Entities;

/**
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class Product {

    private String product_code;
    private String description;
    private double price;

    /**
     * @param product_code
     * @param description
     * @param price
     */
    public Product(String product_code, String description, double price) {
        this.product_code = product_code;
        this.description = description;
        this.price = price;
    }

    /**
     *
     */
    public Product() {
    }

    /**
     * @return
     */
    public String getProduct_code() {
        return product_code;
    }

    /**
     * @param product_code
     */
    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    /**
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return description;
    }

}
