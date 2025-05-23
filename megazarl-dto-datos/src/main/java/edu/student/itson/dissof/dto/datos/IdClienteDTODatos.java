
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;

/**
 * IdClienteDTODatos.java

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
public class IdClienteDTODatos {
    
    /**
     * Objeto Long que representa el ID del cliente.
     */
    private IdEntidadGenericoDatos idCliente;

    /**
     * Constructor de la clase que recibe el ID del cliente.
     * @param idCliente Objeto Long que representa el ID del cliente.
     */
    public IdClienteDTODatos(IdEntidadGenericoDatos idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Método que permite obtener el ID del cliente.
     * @return Objeto Long que representa el ID del cliente.
     */
    public IdEntidadGenericoDatos getIdCliente() {
        return idCliente;
    }
    
    
}
