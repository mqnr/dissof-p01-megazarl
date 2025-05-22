
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;


public abstract class ProductoPedidoDTO {
    
    protected IdEntidadGenerico id;
    
    protected Integer cantidadRequerida; 

    public ProductoPedidoDTO(
            IdEntidadGenerico id,
            Integer cantidadRequerida) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
    }
    
    public ProductoPedidoDTO(
            Integer cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public IdEntidadGenerico getId() {
        return id;
    }

    public Integer getCantidadRequerida() {
        return cantidadRequerida;
    }
    
    public void setId(IdEntidadGenerico id) {
        this.id = id;
    }
    
    public abstract IdEntidadGenerico getIdProducto();
    
    public abstract IdEntidadGenerico getIdPedido();

    

    
}
