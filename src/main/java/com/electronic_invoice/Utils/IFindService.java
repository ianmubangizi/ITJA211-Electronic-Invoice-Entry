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
    boolean findId(int id);

    /**
     *
     * @return
     */
    int lastCreatedId();

    //public <?> withQuery(String sql);

}
