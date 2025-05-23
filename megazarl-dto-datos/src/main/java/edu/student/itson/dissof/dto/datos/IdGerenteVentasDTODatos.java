package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;

/**
 * IdGerenteVentas.java
 *
 * Clase DTO que contiene el ID de un gerente de ventas de la empresa.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 */
public class IdGerenteVentasDTODatos {
    
    /**
     * Objeto IdEntidadGenericoDatos que representa el ID del gerente de ventas.
     */
    private IdEntidadGenericoDatos idGerenteVentas;

    /**
     * Constructor de la clase que recibe el ID del gerente de ventas.
     * @param idGerenteVentas Objeto IdEntidadGenericoDatos que representa el ID del gerente de ventas.
     */
    public IdGerenteVentasDTODatos(IdEntidadGenericoDatos idGerenteVentas) {
        this.idGerenteVentas = idGerenteVentas;
    }

    /**
     * Método que permite obtener el ID del gerente de ventas.
     * @return Objeto IdEntidadGenericoDatos que representa el ID del gerente de ventas.
     */
    public IdEntidadGenericoDatos getIdGerenteVentas() {
        return idGerenteVentas;
    }
 
}