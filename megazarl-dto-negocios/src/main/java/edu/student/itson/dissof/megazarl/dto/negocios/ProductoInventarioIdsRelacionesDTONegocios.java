
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;


public class ProductoInventarioIdsRelacionesDTONegocios extends ProductoInventarioDTONegocios{

    
    private IdEntidadGenericoNegocios idProducto;

    private IdEntidadGenericoNegocios idSucursal;
    
    public ProductoInventarioIdsRelacionesDTONegocios(
            IdEntidadGenericoNegocios idProducto,
            IdEntidadGenericoNegocios idSucursal){
        
        super();
        
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
        
    }

    @Override
    public IdEntidadGenericoNegocios getIdProducto() {
        return idProducto;
    }

    @Override
    public IdEntidadGenericoNegocios getIdSucursal() {
        return idSucursal;
    }

    
}
