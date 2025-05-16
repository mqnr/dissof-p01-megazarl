
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
 * @author Manuel Romo López
 * ID: 00000253080
 *
 */
public class ProductosIdProveedorInvalidoException extends Exception{
    /**
     * Constructor por defecto.
     */
    public ProductosIdProveedorInvalidoException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ProductosIdProveedorInvalidoException(String message) {
        super(message);
    }
}
