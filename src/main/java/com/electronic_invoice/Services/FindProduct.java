package com.electronic_invoice.Services;

import com.electronic_invoice.Utils.IFindService;

/**
 * FindProduct
 */
public class FindProduct implements IFindService {

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
    
}