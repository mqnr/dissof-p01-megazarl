
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * InformacionEnvioProductoProveedorMatrizDTONegocios.java

 Clase DTO que contiene la información necesaria para calcular el costo 
 y tiempo de envío de un producto con la paquetería especificada desde la un 
 proveedor hacia la Matriz.
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
public class InformacionEnvioProductoProveedorMatrizDTONegocios {
    
    /**
     * Objeto Long que representa el ID de la paquetería.
     */
    private IdEntidadGenericoNegocios idPaqueteria;
    
    /**
     * Objeto Long que representa el ID del proveedor.
     */
    private IdEntidadGenericoNegocios idProveedor;
    
    /**
     * Objeto Double que representa el peso total del producto en kilogramos.
     */
    private Double pesoKgTotal;

    /**
     * Constructor de la clase, que recibe el ID de un paquetería y el ID de un proveedor.
     * @param idPaqueteria  Objeto Long que representa el ID de la paquetería.
     * @param idProveedor   Objeto Long que representa el ID del proveedor.
     * @param pesoKgTotal   Objeto Double que representa el peso total del producto en kilogramos.
     */
    public InformacionEnvioProductoProveedorMatrizDTONegocios(
            IdEntidadGenericoNegocios idPaqueteria, 
            IdEntidadGenericoNegocios idProveedor,
            Double pesoKgTotal) {
        
        this.idPaqueteria = idPaqueteria;
        this.idProveedor = idProveedor;
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
     * Método que permite obtener el ID del proveedor.
     * @return Objeto Long que representa el ID del proveedor.
     */
    public IdEntidadGenericoNegocios getIdProveedor() {
        return idProveedor;
    }
    
    /**
     * Método que permite obtener el peso total del producto en kilogramos.
     * @return Objeto Double que representa el peso total del producto en kilogramos.
     */
    public Double getPesoKgTotal() {
        return pesoKgTotal;
    }
    
}
