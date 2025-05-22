
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;

/**
 *  
 * IdAuxiliarVentasDTO.java
 * 
 * Clase DTO que contiene el ID de un Auxiliar de ventas.
 * 
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class IdAuxiliarVentasDTO {
    
    /**
     * Objeto Long que representa el ID del Auxiliar de ventas.
     */
    private IdEntidadGenerico idAuxiliarVentas;

    /**
     * Constructor de la clase que recibe el valor para el ID.
     * @param idAuxiliarVentas Objeto IdEntidadGenerico que representa el ID del Auxiliar de ventas.
     */
    public IdAuxiliarVentasDTO(IdEntidadGenerico idAuxiliarVentas) {
        this.idAuxiliarVentas = idAuxiliarVentas;
    }

    /**
     * Método que permite obtener el ID del Auxiliar de ventas.
     * @return Objeto IdEntidadGenerico que representa el ID del Auxiliar de ventas.
     */
    public IdEntidadGenerico getIdAuxiliarVentas() {
        return idAuxiliarVentas;
    }
    
}
