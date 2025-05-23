
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.CoordinadorLogisticaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCoordinadorLogisticaDTONegocios;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public interface RepositorioCoordinadorLogistica{
    
    public abstract CoordinadorLogisticaDTONegocios recuperarPorId(IdCoordinadorLogisticaDTONegocios idCoordinadorLogisticaDTO);

    public abstract boolean existePorId(IdCoordinadorLogisticaDTONegocios idCoordinadorLogisticaDTO);
    
    public abstract void agregar(CoordinadorLogisticaDTONegocios coordinadorLogistica);

    public abstract void agregar(Collection<CoordinadorLogisticaDTONegocios> coordinadoresLogistica);

    public abstract List<CoordinadorLogisticaDTONegocios> recuperarTodos();

}
