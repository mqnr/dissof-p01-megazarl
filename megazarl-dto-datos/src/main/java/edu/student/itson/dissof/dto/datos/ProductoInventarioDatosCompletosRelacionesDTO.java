
package edu.student.itson.dissof.dto.datos;


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
            Long id,
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
    public Long getIdProducto() {
        return producto.getId();
    }

    @Override
    public Long getIdSucursal() {
        return sucursal.getId();
    }
    
    
}
