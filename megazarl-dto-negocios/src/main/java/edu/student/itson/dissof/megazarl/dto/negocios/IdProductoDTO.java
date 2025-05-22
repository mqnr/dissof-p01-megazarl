
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;

/**
 * IdProductoDTO.java
 *
 * Clase DTO que contiene el ID de un producto en venta.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
public class IdProductoDTO {
    
    /**
     * Objeto IdEntidadGenerico que representa el ID del producto.
     */
    private IdEntidadGenerico idProducto;

    /**
     * Constructor de la clase que recibe el ID del producto.
     * @param idProducto Objeto Long que representa el ID del producto.
     */
    public IdProductoDTO(IdEntidadGenerico idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Método que permite obtener el ID del producto.
     * @return Objeto Long que representa el ID del producto.
     */
    public IdEntidadGenerico getIdProducto() {
        return idProducto;
    }
    
    
}
