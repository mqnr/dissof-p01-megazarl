
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;

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
public class IdPaqueteriaDTO {
    
    /**
     * Objeto Long que representa el ID de la paquetería.
     */
    private IdEntidadGenerico idPaqueteria;

    /**
     * Consutrctor de la clase que recibe el ID de la paquetería.
     * @param idPaqueteria Objeto Long que representa el ID de la paquetería.
     */
    public IdPaqueteriaDTO(IdEntidadGenerico idPaqueteria) {
        this.idPaqueteria = idPaqueteria;
    }

    /**
     * Método que permite obtener el ID de la paquetería.
     * @return Objeto Long que representa el ID de la paquetería.
     */
    public IdEntidadGenerico getIdPaqueteria() {
        return idPaqueteria;
    }
    
    
    
}
