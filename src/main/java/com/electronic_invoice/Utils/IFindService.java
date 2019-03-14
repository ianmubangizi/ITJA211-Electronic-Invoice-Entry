package com.electronic_invoice.Utils;

/**
 * IFindService
 */
public interface IFindService {

    public boolean byId(int id);
    public int lastCreatedId();
    public int findByQuery(String sql);

}