package com.electronic_invoice.Utils;

import com.electronic_invoice.Services.FindCustomer;

/**
 * Generate
 */
public class Generate {

    public int nextCustomerNumber() {
        return (new FindCustomer().lastCreatedId() + 1);
    }
}
