
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;


public class IdProductoPedidoDTODatos {
 
    
    private IdEntidadGenericoDatos idProductoPedido;

    public IdProductoPedidoDTODatos(IdEntidadGenericoDatos idProductoPedido) {
        this.idProductoPedido = idProductoPedido;
    }

    public IdEntidadGenericoDatos getIdProductoPedido() {
        return idProductoPedido;
    }
    
    
}
