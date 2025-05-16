
package edu.student.itson.dissof.megazarl.carritocompras.excepciones;

/**
 * 
 * CarritoComprasIdProductoInventarioInvalidoException.java
 * 
 * Clase que representa una excepción lanzada cuando se determina en el subsistema
 * administradorCarritoCompras que el ID de un producto en inventario recibido es inválido.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Manuel Romo López
 * ID: 00000253080
 */
public class CarritoComprasIdProductoInventarioInvalidoException extends Exception {
    /**
     * Método constructor por defecto
     */
    public CarritoComprasIdProductoInventarioInvalidoException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public CarritoComprasIdProductoInventarioInvalidoException(String message) {
        super(message);
    }
}
