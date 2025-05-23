
package edu.student.itson.dissof.megazarl.carritocompras.excepciones;

/**
 * CarritoComprasAccesoDatosException.java
 * 
 * @author romom
 */
public class CarritoComprasAccesoDatosException extends Exception{
    
    /**
     * Constructor por defecto.
     */
    public CarritoComprasAccesoDatosException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public CarritoComprasAccesoDatosException(String message) {
        super(message);
    }
    
}
