
package edu.student.itson.dissof.megazarl.administradorpedidos.excepciones;

/**
 * PedidosIdSucursalInvalidoException.java
 *
 * Clase que representa una excepción lanzada cuando se intenta utilizar un ID
 * de sucursal que no existe o no es válido en el sistema, dentro del subsistema
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
public class PedidosIdSucursalInvalidoException extends Exception{
    /**
     * Constructor por defecto.
     */
    public PedidosIdSucursalInvalidoException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public PedidosIdSucursalInvalidoException(String message) {
        super(message);
    }
}
