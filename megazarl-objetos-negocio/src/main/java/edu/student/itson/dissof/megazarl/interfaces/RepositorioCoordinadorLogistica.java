
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.CoordinadorLogisticaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCoordinadorLogisticaDTO;


public interface RepositorioCoordinadorLogistica extends RepositorioInmutable<CoordinadorLogisticaDTO>{
    
    public abstract CoordinadorLogisticaDTO recuperarPorId(IdCoordinadorLogisticaDTO idCoordinadorLogisticaDTO);

    public abstract boolean existePorId(IdCoordinadorLogisticaDTO idCoordinadorLogisticaDTO);
    
}
