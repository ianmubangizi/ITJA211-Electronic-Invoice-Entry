package com.electronic_invoice.Utils;

/**
 * ICustomerNotFound
 */
public interface ICustomerNotFound {

    /**
     *
     * @param type
     */
    void createNewCustomer(ECallTypes type);

    /**
     *
     * @param id
     * @return
     */
    int getCreatedId(int id);
}
