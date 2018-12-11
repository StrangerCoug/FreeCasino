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
public class BetTooHighException extends RuntimeException {
    private static final long serialVersionUID = 8527163021665546837L;
    
    public BetTooHighException() {}
    
    public BetTooHighException(String message) {
        super(message);
    }
    
    public BetTooHighException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BetTooHighException(String message, Throwable cause, boolean
            enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    public BetTooHighException(Throwable cause) {
        super(cause);
    }
}
