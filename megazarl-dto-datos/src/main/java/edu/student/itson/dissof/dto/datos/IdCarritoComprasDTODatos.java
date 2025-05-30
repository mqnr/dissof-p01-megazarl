
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;

/**
 * IdCarritoComprasDTODatos.java

 Clase DTO que contiene el ID de un carrito de compras que pertenece a un cliente.
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
public class IdCarritoComprasDTODatos {
    
    /**
     * Objeto IdEntidadGenericoDatos que representa el ID del carrito de compras.
     */
    private IdEntidadGenericoDatos idCarritoCompras;
    
    /**
     * Constructor de la clase que recibe el ID del carrito de compras.
     * @param idCarritoCompras Objeto IdEntidadGenericoDatos que representa el ID del carrito de compras.
     */
    public IdCarritoComprasDTODatos(IdEntidadGenericoDatos idCarritoCompras) {
        this.idCarritoCompras = idCarritoCompras;
    }

    /**
     * Método que permite obtener el ID del carrito de comprass.
     * @return Objeto IdEntidadGenericoDatos que representa el ID del carrito de compras.
     */
    public IdEntidadGenericoDatos getIdCarritoCompras() {
        return idCarritoCompras;
    }
}
