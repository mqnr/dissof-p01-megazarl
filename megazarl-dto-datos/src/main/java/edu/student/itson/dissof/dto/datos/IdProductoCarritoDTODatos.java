
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;

/**
 * @author Manuel Romo López
 */
public class IdProductoCarritoDTODatos {
    
    private IdEntidadGenericoDatos idProductoCarrito;

    public IdProductoCarritoDTODatos(IdEntidadGenericoDatos idProductoCarrito) {
        this.idProductoCarrito = idProductoCarrito;
    }

    public IdEntidadGenericoDatos getIdProductoCarrito() {
        return idProductoCarrito;
    }
    
    
    
}
