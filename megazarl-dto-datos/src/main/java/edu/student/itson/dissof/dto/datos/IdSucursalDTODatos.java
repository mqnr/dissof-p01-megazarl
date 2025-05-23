
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;

/**
 * IdSucursalDTODatos.java

 Clase DTO que contiene el ID de una sucursal de la empresa.
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
public class IdSucursalDTODatos {
    
    /**
     * Objeto IdEntidadGenericoDatos que representa el ID de la sucursal.
     */
    private IdEntidadGenericoDatos idSucursal;

    /**
     * Constructor de la clase que recibe el ID de la sucursal.
     * @param idSucursal Objeto IdEntidadGenericoDatos que representa el ID de la sucursal.
     */
    public IdSucursalDTODatos(IdEntidadGenericoDatos idSucursal) {
        this.idSucursal = idSucursal;
    }

    /**
     * Método que permite obtener el ID de la sucursal.
     * @return Objeto IdEntidadGenericoDatos que representa el ID de la sucursal.
     */
    public IdEntidadGenericoDatos getIdSucursal() {
        return idSucursal;
    }
    
}
