package com.electronic_invoice.Utils;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class CustomerException extends Exception {
    
    private String message = null;
    
    @Override
    public String getMessage() {
        if(!message.isEmpty()){
            return this.message;
        }
        return "com.electronic_invoice.Utils.CustomerException";
    }
    
    /**
     *
     * @param msg
     */
    public void setMessage(String msg){
        this.message = msg;
    };
    
    /**
     *
     * @return
     */
    public static CustomerException customerException(){
        return new CustomerException();
    }
}

class CustomerAreadyExistsException extends CustomerException {

    @Override
    public String getMessage() {
        super.setMessage("Customer Aready Exists");
        return super.getMessage();
    }
    
    public static CustomerAreadyExistsException caee(){
        return new CustomerAreadyExistsException();
    }
}

class CustomerNotFoundException extends CustomerException {

    @Override
    public String getMessage() {
        super.setMessage("Customer Not Found");
        return super.getMessage();
    }
    
    public static CustomerNotFoundException cnfe(){
        return new CustomerNotFoundException();
    }
}