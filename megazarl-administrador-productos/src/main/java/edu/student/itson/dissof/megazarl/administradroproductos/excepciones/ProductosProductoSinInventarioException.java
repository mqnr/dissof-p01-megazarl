
package edu.student.itson.dissof.megazarl.administradroproductos.excepciones;


/**
 * 
 * ProductosProductoSinInventarioException.java
 
 Clase que representa una excepción lanzada cuando un Producto no cuenta
 con inventario disponible.
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
public class ProductosProductoSinInventarioException extends Exception{
    /**
    * Contructor por defecto.
    */
    public ProductosProductoSinInventarioException() {
    }
    
    /**
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ProductosProductoSinInventarioException(String message) {
        super(message);
    }
}
