
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;


public class ProductoInventarioDatosCompletosRelacionesDTONegocios extends ProductoInventarioDTONegocios{
    
    /**
     * Objeto ProductoDTONegocios que representa el producto asociado a este producto en inventario
     */
    private ProductoDTONegocios producto;

    /**
     * Objeto SucursalDTONegocios que representa la sucursal a la que pertenece este producto en inventario
     */
    private SucursalDTONegocios sucursal;
    
    public ProductoInventarioDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            Boolean apartado,
            ProductoDTONegocios producto,
            SucursalDTONegocios sucursal){
        
        super(id, apartado);
        
        this.producto = producto;
        this.sucursal = sucursal;
        
    }
     
    public ProductoInventarioDatosCompletosRelacionesDTONegocios(
            Boolean apartado,
            ProductoDTONegocios producto,
            SucursalDTONegocios sucursal){
        
        super(apartado);
        
        this.producto = producto;
        this.sucursal = sucursal;
        
    }
    
    public ProductoDTONegocios getProducto(){
        return producto;
    }
    
    public SucursalDTONegocios getSucursal(){
        return sucursal;
    }

    @Override
    public IdEntidadGenericoNegocios getIdProducto() {
        return producto.getId();
    }

    @Override
    public IdEntidadGenericoNegocios getIdSucursal() {
        return sucursal.getId();
    }
    
    
}
