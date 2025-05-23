
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;


public abstract class ProductoPedidoDTONegocios {
    
    protected IdEntidadGenericoNegocios id;
    
    protected Integer cantidadRequerida; 

    public ProductoPedidoDTONegocios(
            IdEntidadGenericoNegocios id,
            Integer cantidadRequerida) {
        this.id = id;
        this.cantidadRequerida = cantidadRequerida;
    }
    
    public ProductoPedidoDTONegocios(
            Integer cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public IdEntidadGenericoNegocios getId() {
        return id;
    }

    public Integer getCantidadRequerida() {
        return cantidadRequerida;
    }
    
    public void setId(IdEntidadGenericoNegocios id) {
        this.id = id;
    }
    
    public abstract IdEntidadGenericoNegocios getIdProducto();
    
    public abstract IdEntidadGenericoNegocios getIdPedido();

    

    
}
