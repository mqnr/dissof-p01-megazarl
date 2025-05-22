
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;


public class IdCoordinadorLogisticaDTO {
    
    private IdEntidadGenerico idCoordinadorLogistica;

    public IdCoordinadorLogisticaDTO(IdEntidadGenerico idCoordinadorLogistica) {
        this.idCoordinadorLogistica = idCoordinadorLogistica;
    }

    public IdEntidadGenerico getIdCoordinadorLogistica() {
        return idCoordinadorLogistica;
    }
    
    
}
