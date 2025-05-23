package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * InformacionEnvioProductoMatrizClienteDTONegocios.java

 Clase DTO que contiene la información necesaria para calcular el costo y 
 tiempo de envío de un producto con la paquetería especificada desde la Matriz 
 hacia la dirección del cliente.
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
public class InformacionEnvioProductoMatrizClienteDTONegocios {
    
    /**
     * Objeto Long que representa el ID de la paqueteria.
     */
    private IdEntidadGenericoNegocios idPaqueteria;
    
    /**
     * Objeto Long que representa el ID del cliente.
     */
    private IdEntidadGenericoNegocios idCliente;
    
    /**
     * Objeto Double que representa el peso total del producto en kilogramos.
     */
    private Double pesoKgTotal;

    /**
     * Constructor de la clase que recibe el ID de la paquetería, los datos de la 
     * dirección del cliente, y los datos de la dirección de la Matriz.
     * @param idPaqueteria          Objeto IdEntidadGenericoNegocios que representa el ID de la paqueteria.
     * @param idCliente             Objeto IdEntidadGenericoNegocios que representa el ID del cliente.
     * @param pesoKgTotal           Objeto Double que representa el peso total del producto en kilogramos.
     */
    public InformacionEnvioProductoMatrizClienteDTONegocios(
            IdEntidadGenericoNegocios idPaqueteria,
            IdEntidadGenericoNegocios idCliente,
            Double pesoKgTotal) {
        
        this.idPaqueteria = idPaqueteria;
        this.idCliente = idCliente;
        this.pesoKgTotal = pesoKgTotal;
        
    }

    /**
     * Método que permite obtener el ID de la paqueteria.
     * @return Objeto IdEntidadGenericoNegocios que representa el ID de la paqueteria.
     */
    public IdEntidadGenericoNegocios getIdPaqueteria() {
        return idPaqueteria;
    }

    /**
     * Método que permite obtener el ID del cliente.
     * @return Objeto IdEntidadGenericoNegocios que representa el ID del cliente.
     */
    public IdEntidadGenericoNegocios getIdCliente() {
        return idCliente;
    }

    /**
     * Método que permite obtener el peso total del producto en kilogramos.
     * @return Objeto Double que representa el peso total del producto en kilogramos.
     */
    public Double getPesoKgTotal() {
        return pesoKgTotal;
    }

}