package com.electronic_invoice.Utils;

/**
 * ICustomerNotFound
 */
public interface ICustomerNotFound {

    /**
     *
     * @param type
     */
    public void createNewCustomer(ECallTypes type);

    /**
     *
     * @param id
     * @return
     */
    public int getCreatedId(int id);
}
