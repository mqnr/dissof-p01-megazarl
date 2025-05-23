
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;

/**
 * IdProductoInventarioDTODatos.java

 Clase DTO que contiene el ID de un producto en inventario.
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
public class IdProductoInventarioDTODatos {
    
    /**
     * Objeto IdEntidadGenericoDatos que representa el ID del producto en inventario.
     */
    private IdEntidadGenericoDatos idProductoInventario;

    /**
     * Constructor de la clase que recibe el ID del producto en inventario.
     * @param idProductoInventario Objeto Long que representa el ID del producto en inventario.
     */
    public IdProductoInventarioDTODatos(IdEntidadGenericoDatos idProductoInventario) {
        this.idProductoInventario = idProductoInventario;
    }

    /**
     * Método que permite obtener el ID del producto en inventario.
     * @return Objeto IdEntidadGenericoDatos que representa el ID del producto en inventario.
     */
    public IdEntidadGenericoDatos getIdProductoInventario() {
        return idProductoInventario;
    }
}
