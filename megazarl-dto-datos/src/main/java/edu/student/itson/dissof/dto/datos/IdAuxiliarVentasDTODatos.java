
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;

/**
 *  
 * IdAuxiliarVentasDTODatos.java
 
 Clase DTO que contiene el ID de un Auxiliar de ventas.
 * 
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class IdAuxiliarVentasDTODatos {
    
    /**
     * Objeto Long que representa el ID del Auxiliar de ventas.
     */
    private IdEntidadGenericoDatos idAuxiliarVentas;

    /**
     * Constructor de la clase que recibe el valor para el ID.
     * @param idAuxiliarVentas Objeto IdEntidadGenericoDatos que representa el ID del Auxiliar de ventas.
     */
    public IdAuxiliarVentasDTODatos(IdEntidadGenericoDatos idAuxiliarVentas) {
        this.idAuxiliarVentas = idAuxiliarVentas;
    }

    /**
     * Método que permite obtener el ID del Auxiliar de ventas.
     * @return Objeto IdEntidadGenericoDatos que representa el ID del Auxiliar de ventas.
     */
    public IdEntidadGenericoDatos getIdAuxiliarVentas() {
        return idAuxiliarVentas;
    }
    
}
