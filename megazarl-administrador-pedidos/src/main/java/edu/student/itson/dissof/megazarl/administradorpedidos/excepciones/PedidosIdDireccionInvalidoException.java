
package edu.student.itson.dissof.megazarl.administradorpedidos.excepciones;

/**
 * PedidosIdClienteInvalidoException.java
 *
 * Clase que representa una excepción lanzada cuando se intenta utilizar un ID
 * de dirección inválido, dentro del subsistema administrador de pedidos.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Manuel Romo López
 * ID: 00000253080
 *
 */
public class PedidosIdDireccionInvalidoException extends Exception{
    /**
     * Constructor por defecto.
     */
    public PedidosIdDireccionInvalidoException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public PedidosIdDireccionInvalidoException(String message) {
        super(message);
    }
}
