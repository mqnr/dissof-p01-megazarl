package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;

/**
 * IdGerenteVentas.java
 *
 * Clase DTO que contiene el ID de un gerente de ventas de la empresa.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 */
public class IdGerenteVentasDTO {
    
    /**
     * Objeto Long que representa el ID del gerente de ventas.
     */
    private IdEntidadGenerico idGerenteVentas;

    /**
     * Constructor de la clase que recibe el ID del gerente de ventas.
     * @param idGerenteVentas Objeto Long que representa el ID del gerente de ventas.
     */
    public IdGerenteVentasDTO(IdEntidadGenerico idGerenteVentas) {
        this.idGerenteVentas = idGerenteVentas;
    }

    /**
     * Método que permite obtener el ID del gerente de ventas.
     * @return Objeto Long que representa el ID del gerente de ventas.
     */
    public IdEntidadGenerico getIdGerenteVentas() {
        return idGerenteVentas;
    }
 
}