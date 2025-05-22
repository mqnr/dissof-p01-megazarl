
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;


public class IdCoordinadorLogisticaDTO {
    
    private IdEntidadGenerico idCoordinadorLogistica;

    public IdCoordinadorLogisticaDTO(IdEntidadGenerico idCoordinadorLogistica) {
        this.idCoordinadorLogistica = idCoordinadorLogistica;
    }

    public IdEntidadGenerico getIdCoordinadorLogistica() {
        return idCoordinadorLogistica;
    }
    
    
}
