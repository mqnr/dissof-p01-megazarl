
package edu.student.itson.dissof.megazarl.dto.infraestructura;

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
    private Long idAuxiliarVentas;

    /**
     * Constructor de la clase que recibe el valor para el ID.
     * @param idAuxiliarVentas Objeto Long que representa el ID del Auxiliar de ventas.
     */
    public IdAuxiliarVentasDTO(Long idAuxiliarVentas) {
        this.idAuxiliarVentas = idAuxiliarVentas;
    }

    /**
     * Método que permite obtener el ID del Auxiliar de ventas.
     * @return Objeto Long que representa el ID del Auxiliar de ventas.
     */
    public Long getIdAuxiliarVentas() {
        return idAuxiliarVentas;
    }
    
}
