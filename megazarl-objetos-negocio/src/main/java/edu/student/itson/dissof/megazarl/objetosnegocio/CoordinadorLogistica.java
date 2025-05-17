
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.infraestructura.CoordinadorLogisticaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdCoordinadorLogisticaDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioCoordinadorLogistica;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioCoordinadorLogisticaEnMemoria;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public class CoordinadorLogistica {
    
    private static final RepositorioCoordinadorLogistica repositorio;

    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().coordinadorLogistica()) {
            case MEMORIA -> new RepositorioCoordinadorLogisticaEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }

    public static CoordinadorLogisticaDTO recuperarPorId(IdCoordinadorLogisticaDTO idCoordinadorLogisticaDTO) {
        return repositorio.recuperarPorId(idCoordinadorLogisticaDTO);
    }

    public static boolean existePorId(IdCoordinadorLogisticaDTO idCoordinadorLogisticaDTO) {
        return repositorio.existePorId(idCoordinadorLogisticaDTO);
    }

    public static void agregar(CoordinadorLogisticaDTO coordinadorLogistica) {
        repositorio.agregar(coordinadorLogistica);
    }

    public static void agregar(Collection<CoordinadorLogisticaDTO> coordinadoresLogistica) {
        repositorio.agregar(coordinadoresLogistica);
    }

    public static List<CoordinadorLogisticaDTO> recuperarTodos() {
        return repositorio.recuperarTodos();
    }

    public static boolean existe(Predicate<CoordinadorLogisticaDTO> criterio) {
        return repositorio.existe(criterio);
    }
    
    
    
}
