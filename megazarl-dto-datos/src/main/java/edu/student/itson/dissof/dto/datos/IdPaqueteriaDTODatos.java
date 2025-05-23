
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;

/**
 * Clase DTO que contiene el ID de una paquetería registrada en la empresa.
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
public class IdPaqueteriaDTODatos {
    
    /**
     * Objeto IdEntidadGenericoDatos que representa el ID de la paquetería.
     */
    private IdEntidadGenericoDatos idPaqueteria;

    /**
     * Consutrctor de la clase que recibe el ID de la paquetería.
     * @param idPaqueteria Objeto IdEntidadGenericoDatos que representa el ID de la paquetería.
     */
    public IdPaqueteriaDTODatos(IdEntidadGenericoDatos idPaqueteria) {
        this.idPaqueteria = idPaqueteria;
    }

    /**
     * Método que permite obtener el ID de la paquetería.
     * @return Objeto IdEntidadGenericoDatos que representa el ID de la paquetería.
     */
    public IdEntidadGenericoDatos getIdPaqueteria() {
        return idPaqueteria;
    }
    
    
    
}
