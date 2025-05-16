
package edu.student.itson.dissof.megazarl.dto.infraestructura;


public class ProductoInventarioIdsRelacionesDTO extends ProductoInventarioDTO{

    
    private IdProductoDTO idProducto;

    private IdSucursalDTO idSucursal;
    
    public ProductoInventarioIdsRelacionesDTO(
            IdProductoDTO idProducto,
            IdSucursalDTO idSucursal){
        
        super();
        
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
        
    }

    @Override
    public IdProductoDTO getIdProducto() {
        return idProducto;
    }

    @Override
    public IdSucursalDTO getIdSucursal() {
        return idSucursal;
    }

    
}
