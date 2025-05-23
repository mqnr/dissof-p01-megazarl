
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * IdClienteDTONegocios.java

 Clase DTO que contiene el ID de un cliente de la empresa.
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
public class IdClienteDTONegocios {
    
    /**
     * Objeto Long que representa el ID del cliente.
     */
    private IdEntidadGenericoNegocios idCliente;

    /**
     * Constructor de la clase que recibe el ID del cliente.
     * @param idCliente Objeto Long que representa el ID del cliente.
     */
    public IdClienteDTONegocios(IdEntidadGenericoNegocios idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Método que permite obtener el ID del cliente.
     * @return Objeto Long que representa el ID del cliente.
     */
    public IdEntidadGenericoNegocios getIdCliente() {
        return idCliente;
    }
    
    
}
