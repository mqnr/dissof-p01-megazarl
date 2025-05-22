
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;


public class ProductoInventarioIdsRelacionesDTO extends ProductoInventarioDTO{

    
    private IdEntidadGenerico idProducto;

    private IdEntidadGenerico idSucursal;
    
    public ProductoInventarioIdsRelacionesDTO(
            IdEntidadGenerico idProducto,
            IdEntidadGenerico idSucursal){
        
        super();
        
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
        
    }

    @Override
    public IdEntidadGenerico getIdProducto() {
        return idProducto;
    }

    @Override
    public IdEntidadGenerico getIdSucursal() {
        return idSucursal;
    }

    
}
