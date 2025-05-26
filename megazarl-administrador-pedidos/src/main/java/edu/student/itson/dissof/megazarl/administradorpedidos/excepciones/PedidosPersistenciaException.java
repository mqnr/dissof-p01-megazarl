
package edu.student.itson.dissof.megazarl.administradorpedidos.excepciones;

public class PedidosPersistenciaException extends Exception{
    /**
     * Constructor por defecto.
     */
    public PedidosPersistenciaException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public PedidosPersistenciaException(String message) {
        super(message);
    }
}
