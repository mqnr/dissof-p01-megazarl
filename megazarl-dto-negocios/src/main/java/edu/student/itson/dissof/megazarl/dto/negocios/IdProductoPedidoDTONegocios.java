
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;


public class IdProductoPedidoDTONegocios {
 
    
    private IdEntidadGenericoNegocios idProductoPedido;

    public IdProductoPedidoDTONegocios(IdEntidadGenericoNegocios idProductoPedido) {
        this.idProductoPedido = idProductoPedido;
    }

    public IdEntidadGenericoNegocios getIdProductoPedido() {
        return idProductoPedido;
    }
    
    
}
