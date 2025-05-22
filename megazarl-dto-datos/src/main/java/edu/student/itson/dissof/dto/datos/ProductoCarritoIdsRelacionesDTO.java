
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;


public class ProductoCarritoIdsRelacionesDTO extends ProductoCarritoDTO{


    private IdEntidadGenerico idCarritoCompras;
    
    /**
     * Objeto ProductoDTO que representa el producto dentro del carrito.
     */
    private IdEntidadGenerico idProducto;

    public ProductoCarritoIdsRelacionesDTO(
            IdEntidadGenerico id, 
            Integer cantidad,
            IdEntidadGenerico idCarritoCompras,
            IdEntidadGenerico idProducto) {
        
        super(
            id,
            cantidad);

        this.idCarritoCompras = idCarritoCompras;
        this.idProducto = idProducto;

    }
    
    public ProductoCarritoIdsRelacionesDTO(
            Integer cantidad,
            IdEntidadGenerico idCarritoCompras,
            IdEntidadGenerico idProducto) {
        
        super(
            cantidad);

        this.idCarritoCompras = idCarritoCompras;
        this.idProducto = idProducto;

    }


    @Override
    public IdEntidadGenerico getIdCarritoCompras() {
        return idCarritoCompras;
    }

    @Override
    public IdEntidadGenerico getIdProducto() {
        return idProducto;
    }

    
}
