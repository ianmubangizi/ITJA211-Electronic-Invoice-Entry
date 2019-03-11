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
public class Customer {
    private int customer_number;
    private String name;
    private String address;
    private String city;
    private String province;
    private String zip;

    public Customer() {
    }
    
    public Customer(String customer_number, String name, String address, String city, String province, String zip, String depoist) {
        this.customer_number = Integer.parseInt(customer_number);
        this.name = name;
        this.address = address;
        this.city = city;
        this.province = province;
        this.zip = zip;
        this.depoist = double.class.cast(depoist);
    }
    
    private double depoist;

    public int getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(int customer_number) {
        this.customer_number = customer_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getDepoist() {
        return depoist;
    }

    public void setDepoist(double depoist) {
        this.depoist = depoist;
    }
    
}
