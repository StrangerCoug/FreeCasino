/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.exceptions;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public class InsufficientFundsException extends RuntimeException {
    private static final long serialVersionUID = -2148521630698164553L;
    
    public InsufficientFundsException() {}
    
    public InsufficientFundsException(String message) {
        super(message);
    }
    
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InsufficientFundsException(String message, Throwable cause, boolean
            enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    public InsufficientFundsException(Throwable cause) {
        super(cause);
    }
}
