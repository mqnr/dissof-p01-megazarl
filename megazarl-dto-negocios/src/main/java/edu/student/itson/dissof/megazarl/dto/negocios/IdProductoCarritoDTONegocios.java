
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * @author Manuel Romo López
 */
public class IdProductoCarritoDTONegocios {
    
    private IdEntidadGenericoNegocios idProductoCarrito;

    public IdProductoCarritoDTONegocios(IdEntidadGenericoNegocios idProductoCarrito) {
        this.idProductoCarrito = idProductoCarrito;
    }

    public IdEntidadGenericoNegocios getIdProductoCarrito() {
        return idProductoCarrito;
    }
    
    
    
}
