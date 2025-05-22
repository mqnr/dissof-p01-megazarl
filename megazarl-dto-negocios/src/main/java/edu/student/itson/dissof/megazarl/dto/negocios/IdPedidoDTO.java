
package edu.student.itson.dissof.megazarl.dto.negocios;

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
public class IdPedidoDTO {
    
    /**
     * Objeto Long que representa el ID de un pedido.
     */
    private Long idPedido;

    /**
     * Constructor que recibe el ID dle pedido.
     * @param idPedido 
     */
    public IdPedidoDTO(Long idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * Método que permite obtener el ID del pedido.
     * @return Objeto Long que representa el ID de un pedido.
     */
    public Long getIdPedido() {
        return idPedido;
    }
    
    
    
}
