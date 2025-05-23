
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;


public class ProductoInventarioDTO{
    
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
    private SucursalDTODatos sucursal;
    
    public ProductoInventarioDTO(
            IdEntidadGenericoDatos id,
            Boolean apartado,
            IdEntidadGenericoDatos idProducto,
            SucursalDTODatos sucursal){
        
 
        this.id = id;
        this.apartado = apartado;
        this.idProducto = idProducto;
        this.sucursal = sucursal;
        
    }
     
    public ProductoInventarioDTO(
            Boolean apartado,
            IdEntidadGenericoDatos idProducto,
            SucursalDTODatos sucursal){
        
        this.apartado = apartado;
        this.idProducto = idProducto;
        this.sucursal = sucursal;
        
    }
    
    public IdEntidadGenericoDatos getProducto(){
        return idProducto;
    }
    
    public SucursalDTODatos getSucursal(){
        return sucursal;
    }

    public IdEntidadGenericoDatos getIdProducto() {
        return idProducto;
    }

}
