
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * InformacionEnvioProductoSucursalMatrizDTONegocios.java

 Clase DTO que contiene la información necesaria para calcular el costo 
 y tiempo de envío de un producto con la paquetería especificada desde una sucursal
 hacia la Matriz de la empresa.
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
public class InformacionEnvioProductoSucursalMatrizDTONegocios {
    
    /**
     * Objeto IdEntidadGenericoNegocios que representa el ID de la paquetería.
     */
    private IdEntidadGenericoNegocios idPaqueteria;
    
    /**
     * Objeto IdEntidadGenericoNegocios que representa el ID de la sucursal.
     */
    private IdEntidadGenericoNegocios idSucursal;
    
    /**
     * Objeto Double que representa el peso total en kg a enviar.
     */
    private Double pesoKgTotal;

    /**
     * Consutructor de la clase que recibe el ID de una paquetería y el ID de una
     * sucursal.
     * @param idPaqueteria  Objeto Long que representa el ID de la paquetería.
     * @param idSucursal    Objeto Long que representa el ID de la sucursal.
     * @param pesoKgTotal   Objeto Double que representa el peso total en kg a enviar.
     */
    public InformacionEnvioProductoSucursalMatrizDTONegocios(
            IdEntidadGenericoNegocios idPaqueteria, 
            IdEntidadGenericoNegocios idSucursal,
            Double pesoKgTotal) {
        
        this.idPaqueteria = idPaqueteria;
        this.idSucursal = idSucursal;
        this.pesoKgTotal = pesoKgTotal;
    }

    /**
     * Método que permite obtener el ID de la paquetería.
     * @return Objeto Long que representa el ID de la paquetería. 
     */
    public IdEntidadGenericoNegocios getIdPaqueteria() {
        return idPaqueteria;
    }

    /**
     * Método que permite obtener el ID de la sucursal.
     * @return Objeto Long que representa el ID de la sucursal.
     */
    public IdEntidadGenericoNegocios getIdSucursal() {
        return idSucursal;
    }

    /**
     * Método que permite obtener el peso total en kg a enviar.
     * @return Objeto Double que representa el peso total en kg a enviar.
     */
    public Double getPesoKgTotal() {
        return pesoKgTotal;
    }
    
    
    
    
    
}
