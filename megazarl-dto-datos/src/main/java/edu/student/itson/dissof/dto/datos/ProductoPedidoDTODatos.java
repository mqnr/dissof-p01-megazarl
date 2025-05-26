
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;


public class ProductoPedidoDTODatos {
    
    
    private IdEntidadGenericoDatos id;
    
    private IdEntidadGenericoDatos idProducto;
    
    private IdEntidadGenericoDatos idPedido;
    
    private Integer cantidad;
    

    public ProductoPedidoDTODatos(
            IdEntidadGenericoDatos id,
            IdEntidadGenericoDatos idProducto,
            IdEntidadGenericoDatos idPedido,
            Integer cantidad) {
        
        this.id = id;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }
    
    public ProductoPedidoDTODatos(
            IdEntidadGenericoDatos idPedido,
            IdEntidadGenericoDatos idProducto,
            Integer cantidad) {
        
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public IdEntidadGenericoDatos getId() {
        return id;
    }

    public IdEntidadGenericoDatos getIdProducto() {
        return idProducto;
    }  

    public IdEntidadGenericoDatos getIdPedido() {
        return idPedido;
    }
    
    public Integer getCantidad() {
        return cantidad;
    }
    
    
    
}
