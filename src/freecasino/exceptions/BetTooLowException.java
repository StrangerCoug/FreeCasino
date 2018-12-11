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
public class BetTooLowException extends RuntimeException {
    private static final long serialVersionUID = 5777428818743761377L;
    
    public BetTooLowException() {}
    
    public BetTooLowException(String message) {
        super(message);
    }
    
    public BetTooLowException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BetTooLowException(String message, Throwable cause, boolean
            enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    public BetTooLowException(Throwable cause) {
        super(cause);
    }
}
