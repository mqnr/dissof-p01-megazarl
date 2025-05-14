package edu.student.itson.dissof.megazarl.dto.infraestructura;
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
    private Long idGerenteVentas;

    /**
     * Constructor de la clase que recibe el ID del gerente de ventas.
     * @param idGerenteVentas Objeto Long que representa el ID del gerente de ventas.
     */
    public IdGerenteVentasDTO(Long idGerenteVentas) {
        this.idGerenteVentas = idGerenteVentas;
    }

    /**
     * Método que permite obtener el ID del gerente de ventas.
     * @return Objeto Long que representa el ID del gerente de ventas.
     */
    public Long getIdGerenteVentas() {
        return idGerenteVentas;
    }
 
}