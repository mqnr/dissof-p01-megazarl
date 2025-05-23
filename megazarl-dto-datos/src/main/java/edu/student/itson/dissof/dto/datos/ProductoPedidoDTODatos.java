
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;


public class ProductoPedidoDTODatos {
    
    
    private IdEntidadGenericoDatos id;
    
    private Integer cantidad;

    public ProductoPedidoDTODatos(IdEntidadGenericoDatos id, Integer cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }
    
}
