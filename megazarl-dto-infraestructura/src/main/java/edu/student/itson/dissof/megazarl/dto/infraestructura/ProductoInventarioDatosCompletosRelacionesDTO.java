
package edu.student.itson.dissof.megazarl.dto.infraestructura;


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
            ProductoDTO producto,
            SucursalDTO sucursal){
        
        super();
        
        this.producto = producto;
        this.sucursal = sucursal;
        
    }
    
    public ProductoDTO getProducto(){
        return producto;
    }

    @Override
    public IdProductoDTO getIdProducto() {
        return new IdProductoDTO(producto.getId());
    }

    @Override
    public IdSucursalDTO getIdSucursal() {
        return new IdSucursalDTO(sucursal.getId());
    }
    
    
}
