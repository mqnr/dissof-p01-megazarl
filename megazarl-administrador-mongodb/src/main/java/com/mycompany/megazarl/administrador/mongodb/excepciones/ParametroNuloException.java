
package com.mycompany.megazarl.administrador.mongodb.excepciones;

/**
 * ParametroNuloException.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class ParametroNuloException extends Exception{
    /**
     * Constructor por defecto.
     */
    public ParametroNuloException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ParametroNuloException(String message) {
        super(message);
    }
}
