/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.ex;

/**
 *
 * @author rogeri
 */
public abstract class MonopolyException extends Exception {
    private String message;
    
    public MonopolyException(String detail) {
        super(detail);
        this.message = detail;
    }
    
    public String getMessage() {
        return message;
    }
}
