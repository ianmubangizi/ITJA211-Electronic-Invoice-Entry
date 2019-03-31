package com.electronic_invoice.Utils;

/**
 * IFindService
 */
public interface IFindService {

    /**
     *
     * @param id
     * @return
     */
    public boolean findId(int id);

    /**
     *
     * @return
     */
    public int lastCreatedId();

    //public <?> withQuery(String sql);

}
