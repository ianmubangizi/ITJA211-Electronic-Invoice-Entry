package com.electronic_invoice.Entities;

/**
 * Customer Entity Class
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
    private double deposit;

    /**
     * Customer Constructor
     *
     * @param customer_number The Customer's number
     * @param name            The name of the Customer
     * @param address         The Customer's street address
     * @param city            The name of City
     * @param province        The province show be formated as follows
     *                        <code> ZA-WC </code> where ZA is the country and WC is
     *                        the short hand of the province
     * @param zip             The zip should be a max of length of 5 integers
     * @param deposit         The deposit the customer makes on creation
     */
    public Customer(int customer_number, String name, String address, String city, String province, String zip,
            double deposit) {
        this.customer_number = customer_number;
        this.name = name;
        this.address = address;
        this.city = city;
        this.province = province;
        this.zip = zip;
        this.deposit = deposit;
    }

    public Customer() {
    }

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

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

}
