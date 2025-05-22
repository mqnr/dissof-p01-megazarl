
package edu.student.itson.dissof.megazarl.dto.negocios;


public class ProductoInventarioIdsRelacionesDTO extends ProductoInventarioDTO{

    
    private Long idProducto;

    private Long idSucursal;
    
    public ProductoInventarioIdsRelacionesDTO(
            Long idProducto,
            Long idSucursal){
        
        super();
        
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
        
    }

    @Override
    public Long getIdProducto() {
        return idProducto;
    }

    @Override
    public Long getIdSucursal() {
        return idSucursal;
    }

    
}
