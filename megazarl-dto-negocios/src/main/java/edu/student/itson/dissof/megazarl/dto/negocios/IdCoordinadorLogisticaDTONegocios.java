
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;


public class IdCoordinadorLogisticaDTONegocios {
    
    private IdEntidadGenericoNegocios idCoordinadorLogistica;

    public IdCoordinadorLogisticaDTONegocios(IdEntidadGenericoNegocios idCoordinadorLogistica) {
        this.idCoordinadorLogistica = idCoordinadorLogistica;
    }

    public IdEntidadGenericoNegocios getIdCoordinadorLogistica() {
        return idCoordinadorLogistica;
    }
    
    
}
