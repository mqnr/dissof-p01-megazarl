package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;

/**
 * IdClientePaqueteriaDTO.java
 * 
 * Clase DTO contiene los identificadores del cliente y la paquetería seleccionada, necesarios
 * para realizar el cálculo del costo de envío de un pedido.
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
public class IdClientePaqueteriaDTO {
    /**
     * Objeto Long que representa el ID del cliente.
     */
    private IdEntidadGenerico idCliente;
    
    /**
     * Objeto Long que representa el ID de la paquetería.
     */
    private IdEntidadGenerico idPaqueteria;

    /**
     * Consutructor que recibe el ID del cliente y el ID de la paquetería.
     * @param idCliente     Objeto IdEntidadGenerico que representa el ID del cliente.
     * @param idPaqueteria  Objeto IdEntidadGenerico que representa el ID de la paquetería.
     */
    public IdClientePaqueteriaDTO(
            IdEntidadGenerico idCliente,
            IdEntidadGenerico idPaqueteria) {
        
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
    }

    /**
     * Método que permite obtener el ID del cliente.
     * @return Objeto Long que representa el ID del cliente.
     */
    public IdEntidadGenerico getIdCliente() {
        return idCliente;
    }

    /**
     * Método que permite obtenre el ID de la paquetería.
     * @return Objeto Long que representa el ID de la paquetería.
     */
    public IdEntidadGenerico getIdPaqueteria() {
        return idPaqueteria;
    }
}
