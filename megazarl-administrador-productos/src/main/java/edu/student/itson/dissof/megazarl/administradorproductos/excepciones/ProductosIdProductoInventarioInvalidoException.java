
package edu.student.itson.dissof.megazarl.administradorproductos.excepciones;

/**
 * ProductosIdProductoInvalidoException.java
 *
 * Clase que representa una excepción lanzada cuando se intenta utilizar un ID
 * de un producto en invenario que no existe o no es válido en el sistema, dentro del subsistema
 * administrador de productos.
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
 *
 */
public class ProductosIdProductoInventarioInvalidoException extends Exception{
    /**
    * Contructor por defecto.
    */
    public ProductosIdProductoInventarioInvalidoException() {
    }
    
    /**
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ProductosIdProductoInventarioInvalidoException(String message) {
        super(message);
    }
}
