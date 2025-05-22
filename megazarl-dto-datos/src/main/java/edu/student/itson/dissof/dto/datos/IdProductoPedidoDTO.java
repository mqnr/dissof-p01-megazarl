
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;


public class IdProductoPedidoDTO {
 
    
    private IdEntidadGenerico idProductoPedido;

    public IdProductoPedidoDTO(IdEntidadGenerico idProductoPedido) {
        this.idProductoPedido = idProductoPedido;
    }

    public IdEntidadGenerico getIdProductoPedido() {
        return idProductoPedido;
    }
    
    
}
