
package edu.student.itson.dissof.megazarl.dto.infraestructura;

/**
 * IdSucursalDTO.java
 *
 * Clase DTO que contiene el ID de una sucursal de la empresa.
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
public class IdSucursalDTO {
    
    /**
     * Objeto Long que representa el ID de la sucursal.
     */
    private Long idSucursal;

    /**
     * Constructor de la clase que recibe el ID de la sucursal.
     * @param idSucursal Objeto Long que representa el ID de la sucursal.
     */
    public IdSucursalDTO(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    /**
     * Método que permite obtener el ID de la sucursal.
     * @return Objeto Long que representa el ID de la sucursal.
     */
    public Long getIdSucursal() {
        return idSucursal;
    }
    
}
