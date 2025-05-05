
package edu.student.itson.dissof.megazarl.administradorpedidos.excepciones;


/**
 * PedidosIdProveedorInvalidoException.java
 *
 * Clase que representa una excepción lanzada cuando se intenta utilizar un ID
 * de proveedor que no existe o no es válido en el sistema, dentro del subsistema
 * administrador de pedidos.
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
public class PedidosIdProveedorInvalidoException extends Exception{
    /**
     * Constructor por defecto.
     */
    public PedidosIdProveedorInvalidoException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public PedidosIdProveedorInvalidoException(String message) {
        super(message);
    }
}
