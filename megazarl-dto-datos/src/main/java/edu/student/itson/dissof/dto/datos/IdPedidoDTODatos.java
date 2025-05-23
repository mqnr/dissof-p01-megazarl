
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;

/**
 * Clase DTO que contiene el ID de un pedido realizado por un cliente.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class IdPedidoDTODatos {
    
    /**
     * Objeto IdEntidadGenericoDatos que representa el ID de un pedido.
     */
    private IdEntidadGenericoDatos idPedido;

    /**
     * Constructor que recibe el ID dle pedido.
     * @param idPedido 
     */
    public IdPedidoDTODatos(IdEntidadGenericoDatos idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * Método que permite obtener el ID del pedido.
     * @return Objeto IdEntidadGenericoDatos que representa el ID de un pedido.
     */
    public IdEntidadGenericoDatos getIdPedido() {
        return idPedido;
    }
    
    
    
}
