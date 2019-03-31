package com.electronic_invoice.Services.Adders;

import com.electronic_invoice.Entities.LineItem;
import static com.electronic_invoice.Services.DatabaseService.databaseService;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public final class AddLineItem {

    private static AddLineItem service = null;

    private AddLineItem() {
        service = this;
    }

    /**
     *
     * @return
     */
    public static AddLineItem lineItemService() {
        if(service == null)
            new AddLineItem();
        return service;
    }
    
    /**
     *
     * @param item
     */
    public void create(LineItem item) {
        databaseService().updateQuery(String.format(
                "INSERT INTO `orion`.`lineitem` (`Invoice_Number`,`Product_Code`,`Quantity`) VALUES ('%s','%s',%s);",
                item.getInvoice_number(), item.getProduct_code(), item.getQuantity()));
    }
}
