
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioDireccion;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioDireccionEnMemoria;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public class Direccion {
    private static final RepositorioDireccion repositorio;

    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().direccion()) {
            case MEMORIA -> new RepositorioDireccionEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }

    public static DireccionDTONegocios recuperarPorId(IdDireccionDTONegocios idDireccionDTO) {
        return repositorio.recuperarPorId(idDireccionDTO);
    }

    public static boolean existePorId(IdDireccionDTONegocios idDireccionDTO) {
        return repositorio.existePorId(idDireccionDTO);
    }

    public static DireccionDTONegocios actualizar(ActualizacionDireccionDTONegocios actualizacionDireccionDTO) {
        return repositorio.actualizar(actualizacionDireccionDTO);
    }

    public static void agregar(DireccionDTONegocios direccion) {
        repositorio.agregar(direccion);
    }

    public static void agregar(Collection<DireccionDTONegocios> direcciones) {
        repositorio.agregar(direcciones);
    }

    public static List<DireccionDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }

}
