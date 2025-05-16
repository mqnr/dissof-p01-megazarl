
package edu.student.itson.dissof.megazarl.carritocompras.excepciones;

/**
 * 
 * CarritoComprasIdClienteInvalidoException.java
 * 
 * Clase que representa una excepción lanzada cuando el ID de una dirección
 * es inválido, dentro del subsistema administradorCarritoCompras.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Manuel Romo López
 * ID: 00000253080
 */
public class CarritoComprasIdDireccionInvalidoException extends Exception{
    /**
     * Contructor por defecto.
     */
    public CarritoComprasIdDireccionInvalidoException() {
    }
    
    /**
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public CarritoComprasIdDireccionInvalidoException(String message) {
        super(message);
    }
}
