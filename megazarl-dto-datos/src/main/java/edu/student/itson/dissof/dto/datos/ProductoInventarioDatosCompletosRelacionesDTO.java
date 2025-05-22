
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;


public class ProductoInventarioDatosCompletosRelacionesDTO extends ProductoInventarioDTO{
    
    /**
     * Objeto ProductoDTO que representa el producto asociado a este producto en inventario
     */
    private ProductoDTO producto;

    /**
     * Objeto SucursalDTO que representa la sucursal a la que pertenece este producto en inventario
     */
    private SucursalDTO sucursal;
    
    public ProductoInventarioDatosCompletosRelacionesDTO(
            IdEntidadGenerico id,
            Boolean apartado,
            ProductoDTO producto,
            SucursalDTO sucursal){
        
        super(id, apartado);
        
        this.producto = producto;
        this.sucursal = sucursal;
        
    }
     
    public ProductoInventarioDatosCompletosRelacionesDTO(
            Boolean apartado,
            ProductoDTO producto,
            SucursalDTO sucursal){
        
        super(apartado);
        
        this.producto = producto;
        this.sucursal = sucursal;
        
    }
    
    public ProductoDTO getProducto(){
        return producto;
    }
    
    public SucursalDTO getSucursal(){
        return sucursal;
    }

    @Override
    public IdEntidadGenerico getIdProducto() {
        return producto.getId();
    }

    @Override
    public IdEntidadGenerico getIdSucursal() {
        return sucursal.getId();
    }
    
    
}
