
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;


public class ProductoInventarioDTODatos{
    
    /**
     * Objeto IdEntidadGenericoDatos que representa el id del producto en inventario
     */
    private IdEntidadGenericoDatos id;
    
    private Boolean apartado;
    
    /**
     * Objeto ProductoDTO que representa el producto asociado a este producto en inventario
     */
    private IdEntidadGenericoDatos idProducto;

    /**
     * Objeto SucursalDTO que representa la sucursal a la que pertenece este producto en inventario
     */
    private IdEntidadGenericoDatos idSucursal;
    
    public ProductoInventarioDTODatos(
            IdEntidadGenericoDatos id,
            Boolean apartado,
            IdEntidadGenericoDatos idProducto,
            IdEntidadGenericoDatos idSucursal){
        
 
        this.id = id;
        this.apartado = apartado;
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
        
    }
     
    public ProductoInventarioDTODatos(
            Boolean apartado,
            IdEntidadGenericoDatos idProducto,
            IdEntidadGenericoDatos idSucursal){
        
        this.apartado = apartado;
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
        
    }

    public IdEntidadGenericoDatos getId() {
        return id;
    }

    public Boolean getApartado() {
        return apartado;
    }
    
    public IdEntidadGenericoDatos getIdProducto(){
        return idProducto;
    }
    
    public IdEntidadGenericoDatos getIdSucursal(){
        return idSucursal;
    }

}
