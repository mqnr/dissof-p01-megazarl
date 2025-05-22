
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioDireccion;
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

    public static DireccionDTO recuperarPorId(IdDireccionDTO idDireccionDTO) {
        return repositorio.recuperarPorId(idDireccionDTO);
    }

    public static boolean existePorId(IdDireccionDTO idDireccionDTO) {
        return repositorio.existePorId(idDireccionDTO);
    }

    public static DireccionDTO actualizar(ActualizacionDireccionDTO actualizacionDireccionDTO) {
        return repositorio.actualizar(actualizacionDireccionDTO);
    }

    public static void agregar(DireccionDTO direccion) {
        repositorio.agregar(direccion);
    }

    public static void agregar(Collection<DireccionDTO> direcciones) {
        repositorio.agregar(direcciones);
    }

    public static List<DireccionDTO> recuperarTodos() {
        return repositorio.recuperarTodos();
    }

    public static boolean existe(Predicate<DireccionDTO> criterio) {
        return repositorio.existe(criterio);
    }
}
