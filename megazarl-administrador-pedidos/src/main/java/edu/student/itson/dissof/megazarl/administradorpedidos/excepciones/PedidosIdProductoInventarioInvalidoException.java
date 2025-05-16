
package edu.student.itson.dissof.megazarl.administradorpedidos.excepciones;

/**
 * PedidosIdProductoInvalidoException.java
 *
 * Clase que representa una excepción lanzada cuando se intenta utilizar un ID
 * de producto en inventario que no existe o no es válido en el sistema, dentro del subsistema
 * administrador de pedidos.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 *
 */
public class PedidosIdProductoInventarioInvalidoException extends Exception{
    
    /**
     * Constructor por defecto.
     */
    public PedidosIdProductoInventarioInvalidoException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public PedidosIdProductoInventarioInvalidoException(String message) {
        super(message);
    }
    
}
