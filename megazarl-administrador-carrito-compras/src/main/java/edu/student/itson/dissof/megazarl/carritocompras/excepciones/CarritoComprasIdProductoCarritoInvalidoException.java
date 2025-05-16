
package edu.student.itson.dissof.megazarl.carritocompras.excepciones;

/**
 * 
 * CarritoComprasIdClienteInvalidoException.java
 * 
 * Clase que representa una excepción lanzada cuando el ID de un producto en un
 * carrito de compras es inválido, dentro del subsistema administradorCarritoCompras.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */

public class CarritoComprasIdProductoCarritoInvalidoException extends Exception{
    /**
     * Contructor por defecto.
     */
    public CarritoComprasIdProductoCarritoInvalidoException() {
    }
    
    /**
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public CarritoComprasIdProductoCarritoInvalidoException(String message) {
        super(message);
    }
}
