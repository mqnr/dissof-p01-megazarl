
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;


public class ProductoPedidoDTONegocios {
    
    private IdEntidadGenericoNegocios id;
    
    private IdEntidadGenericoNegocios idProducto;
    
    private IdEntidadGenericoNegocios idPedido;
    
    private Integer cantidadRequerida; 

    public ProductoPedidoDTONegocios(
            IdEntidadGenericoNegocios id,
            IdEntidadGenericoNegocios idProducto,
            Integer cantidadRequerida) {
        
        this.id = id;
        this.idProducto = idProducto;
        this.cantidadRequerida = cantidadRequerida;
    }
    
    public ProductoPedidoDTONegocios(
            IdEntidadGenericoNegocios idProducto,
            Integer cantidadRequerida) {
        
        this.idProducto = idProducto;
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

    public IdEntidadGenericoNegocios getIdProducto() {
        return idProducto;
    }
    
    public IdEntidadGenericoNegocios getIdPedido() {
        return idPedido;
    }
    
}
