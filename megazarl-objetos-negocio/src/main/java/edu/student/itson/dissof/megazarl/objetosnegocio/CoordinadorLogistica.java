
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.CoordinadorLogisticaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCoordinadorLogisticaDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioCoordinadorLogistica;
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

    public static CoordinadorLogisticaDTONegocios recuperarPorId(IdCoordinadorLogisticaDTONegocios idCoordinadorLogisticaDTO) {
        return repositorio.recuperarPorId(idCoordinadorLogisticaDTO);
    }

    public static boolean existePorId(IdCoordinadorLogisticaDTONegocios idCoordinadorLogisticaDTO) {
        return repositorio.existePorId(idCoordinadorLogisticaDTO);
    }

    public static void agregar(CoordinadorLogisticaDTONegocios coordinadorLogistica) {
        repositorio.agregar(coordinadorLogistica);
    }

    public static void agregar(Collection<CoordinadorLogisticaDTONegocios> coordinadoresLogistica) {
        repositorio.agregar(coordinadoresLogistica);
    }

    public static List<CoordinadorLogisticaDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
}
