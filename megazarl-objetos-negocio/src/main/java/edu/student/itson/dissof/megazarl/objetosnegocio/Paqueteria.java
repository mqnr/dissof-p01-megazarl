package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioPaqueteria;
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

    public static PaqueteriaDTONegocios recuperarPorId(IdPaqueteriaDTONegocios idPaqueteriaDTO) {
        return repositorio.recuperarPorId(idPaqueteriaDTO);
    }

    public static boolean existePorId(IdPaqueteriaDTONegocios idPaqueteriaDTO) {
        return repositorio.existePorId(idPaqueteriaDTO);
    }

    public static void agregar(PaqueteriaDTONegocios paqueteriaDTO) {
        repositorio.agregar(paqueteriaDTO);
    }

    public static void agregar(Collection<PaqueteriaDTONegocios> paqueterias) {
        repositorio.agregar(paqueterias);
    }

    public static List<PaqueteriaDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
}
