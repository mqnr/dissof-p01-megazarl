
package edu.student.itson.dissof.megazarl.carritocompras.excepciones;

/**
 * CarritoComprasProductoSinInventarioException.java
 * 
 * Clase que representa una excepción lanzada cuando no hay existencias de un 
 * Producto y se intenta agregar al carrito.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 */
public class CarritoComprasProductoSinInventarioException extends Exception{

    /**
     * Constrctor por defecto
     */
    public CarritoComprasProductoSinInventarioException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public CarritoComprasProductoSinInventarioException(String message) {
        super(message);
    }
    
}
