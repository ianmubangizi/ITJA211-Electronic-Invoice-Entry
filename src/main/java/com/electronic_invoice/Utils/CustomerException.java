package com.electronic_invoice.Utils;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class CustomerException extends Exception {

    private static final long serialVersionUID = 4424053951740697087L;
    private String message = null;

    @Override
    public String getMessage() {
        if (!message.isEmpty()) {
            return this.message;
        }
        return "com.electronic_invoice.Utils.CustomerException";
    }

    /**
     *
     * @param msg
     */
    public void setMessage(String msg) {
        this.message = msg;
    };

    /**
     *
     * @return
     */
    public static CustomerException customerException() {
        return new CustomerException();
    }
}

class CustomerAlreadyExistsException extends CustomerException {

    private static final long serialVersionUID = -3079153839388446745L;

    @Override
    public String getMessage() {
        super.setMessage("Customer Already Exists");
        return super.getMessage();
    }

    public static CustomerAlreadyExistsException caee() {
        return new CustomerAlreadyExistsException();
    }
}

class CustomerNotFoundException extends CustomerException {

    private static final long serialVersionUID = -2808675380375175613L;

    @Override
    public String getMessage() {
        super.setMessage("Customer Not Found");
        return super.getMessage();
    }

    public static CustomerNotFoundException cnfe() {
        return new CustomerNotFoundException();
    }
}