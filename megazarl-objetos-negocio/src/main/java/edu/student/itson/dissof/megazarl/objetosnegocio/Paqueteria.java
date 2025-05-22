package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioPaqueteria;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioPaqueteriaEnMemoria;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Paqueteria {
    private static final RepositorioPaqueteria repositorio;

    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().paqueteria()) {
            case MEMORIA -> new RepositorioPaqueteriaEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }

    public static PaqueteriaDTO recuperarPorId(IdPaqueteriaDTO idPaqueteriaDTO) {
        return repositorio.recuperarPorId(idPaqueteriaDTO);
    }

    public static boolean existePorId(IdPaqueteriaDTO idPaqueteriaDTO) {
        return repositorio.existePorId(idPaqueteriaDTO);
    }

    public static void agregar(PaqueteriaDTO paqueteriaDTO) {
        repositorio.agregar(paqueteriaDTO);
    }

    public static void agregar(Collection<PaqueteriaDTO> paqueterias) {
        repositorio.agregar(paqueterias);
    }

    public static List<PaqueteriaDTO> recuperarTodos() {
        return repositorio.recuperarTodos();
    }

    public static Stream<PaqueteriaDTO> stream() {
        return repositorio.stream();
    }
    
    public static boolean existe(Predicate<PaqueteriaDTO> criterio) {
        return repositorio.existe(criterio);
    }
    
}
