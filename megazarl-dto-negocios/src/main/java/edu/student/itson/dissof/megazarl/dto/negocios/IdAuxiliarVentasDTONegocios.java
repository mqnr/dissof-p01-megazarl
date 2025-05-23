
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 *  
 * IdAuxiliarVentasDTONegocios.java
 
 Clase DTO que contiene el ID de un Auxiliar de ventas.
 * 
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class IdAuxiliarVentasDTONegocios {
    
    /**
     * Objeto Long que representa el ID del Auxiliar de ventas.
     */
    private IdEntidadGenericoNegocios idAuxiliarVentas;

    /**
     * Constructor de la clase que recibe el valor para el ID.
     * @param idAuxiliarVentas Objeto Long que representa el ID del Auxiliar de ventas.
     */
    public IdAuxiliarVentasDTONegocios(IdEntidadGenericoNegocios idAuxiliarVentas) {
        this.idAuxiliarVentas = idAuxiliarVentas;
    }

    /**
     * Método que permite obtener el ID del Auxiliar de ventas.
     * @return Objeto Long que representa el ID del Auxiliar de ventas.
     */
    public IdEntidadGenericoNegocios getIdAuxiliarVentas() {
        return idAuxiliarVentas;
    }
    
}
