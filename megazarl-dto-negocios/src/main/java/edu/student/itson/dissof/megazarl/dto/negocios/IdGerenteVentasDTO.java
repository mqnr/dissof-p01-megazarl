package edu.student.itson.dissof.megazarl.dto.negocios;
/**
 * IdGerenteVentasDTO.java
 *
 * Clase DTO que contiene el ID de un gerente de ventas de la empresa.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 *
 */
public class IdGerenteVentasDTO {
    
    private Long idGerenteVentas;

    public IdGerenteVentasDTO(Long idGerenteVentas) {
        this.idGerenteVentas = idGerenteVentas;
    }

    public Long getIdGerenteVentas() {
        return idGerenteVentas;
    }

}