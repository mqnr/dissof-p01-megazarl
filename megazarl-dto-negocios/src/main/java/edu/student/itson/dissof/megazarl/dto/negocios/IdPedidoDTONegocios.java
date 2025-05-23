
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

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
public class IdPedidoDTONegocios {
    
    /**
     * Objeto Long que representa el ID de un pedido.
     */
    private IdEntidadGenericoNegocios idPedido;

    /**
     * Constructor que recibe el ID dle pedido.
     * @param idPedido 
     */
    public IdPedidoDTONegocios(IdEntidadGenericoNegocios idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * Método que permite obtener el ID del pedido.
     * @return Objeto Long que representa el ID de un pedido.
     */
    public IdEntidadGenericoNegocios getIdPedido() {
        return idPedido;
    }
    
    
    
}
