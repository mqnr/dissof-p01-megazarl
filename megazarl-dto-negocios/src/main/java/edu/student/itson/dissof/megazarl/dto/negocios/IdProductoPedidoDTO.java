
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;


public class IdProductoPedidoDTO {
 
    
    private IdEntidadGenerico idProductoPedido;

    public IdProductoPedidoDTO(IdEntidadGenerico idProductoPedido) {
        this.idProductoPedido = idProductoPedido;
    }

    public IdEntidadGenerico getIdProductoPedido() {
        return idProductoPedido;
    }
    
    
}
