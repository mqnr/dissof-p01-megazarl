
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;

/**
 * @author Manuel Romo López
 */
public class IdProductoCarritoDTO {
    
    private IdEntidadGenerico idProductoCarrito;

    public IdProductoCarritoDTO(IdEntidadGenerico idProductoCarrito) {
        this.idProductoCarrito = idProductoCarrito;
    }

    public IdEntidadGenerico getIdProductoCarrito() {
        return idProductoCarrito;
    }
    
    
    
}
