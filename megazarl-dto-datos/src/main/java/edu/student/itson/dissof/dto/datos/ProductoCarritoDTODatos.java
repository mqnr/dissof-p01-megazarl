
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;


public class ProductoCarritoDTODatos {

    /**
     * Objeto Long que representa el ID del producto en el carrito.
     */
    private IdEntidadGenericoDatos id;
    
    /**
     * Objeto Integer que representa la cantidad del producto en el carrito.
     */
    private Integer cantidad;
    
    /**
     * Objeto ProductoDTO que representa el producto dentro del carrito.
     */
    private IdEntidadGenericoDatos idProducto;
    
    private IdEntidadGenericoDatos idCliente;

    public ProductoCarritoDTODatos(
            IdEntidadGenericoDatos id, 
            Integer cantidad,
            IdEntidadGenericoDatos idProducto) {
        
        this.id = id;
        this.cantidad = cantidad;
        this.idProducto = idProducto;

    }
    
    
    public ProductoCarritoDTODatos(
            Integer cantidad,
            IdEntidadGenericoDatos idCliente,
            IdEntidadGenericoDatos idProducto) {
        
        this.cantidad = cantidad;
        this.idCliente = idCliente;
        this.idProducto = idProducto;

    }
    
    public ProductoCarritoDTODatos(
            Integer cantidad,
            IdEntidadGenericoDatos idProducto) {
        
        this.cantidad = cantidad;
        this.idProducto = idProducto;

    }
    
    public IdEntidadGenericoDatos getIdProducto(){
        return idProducto;
    }

    public IdEntidadGenericoDatos getId() {
        return id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public IdEntidadGenericoDatos getIdCliente() {
        return idCliente;
    }
    

}
