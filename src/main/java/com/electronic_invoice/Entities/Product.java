/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice.Entities;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class Product {

    private String product_code;
    private String description;
    private double price;

    public Product(String product_code, String description, double price) {
        this.product_code = product_code;
        this.description = description;
        this.price = price;
    }

    public Product() {
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return product_code + " [" + description + "] R" + price;
    }

}
