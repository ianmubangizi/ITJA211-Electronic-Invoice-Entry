package com.electronic_invoice.Services;

import com.electronic_invoice.Utils.IFindService;

/**
 * FindInvoice
 */
public class FindInvoice implements IFindService {

    @Override
    public boolean byId(int id) {
        return false;
    }

    @Override
    public int lastCreatedId() {
        return 0;
    }

    @Override
    public int findByQuery(String sql) {
        return 0;
    }

    
}