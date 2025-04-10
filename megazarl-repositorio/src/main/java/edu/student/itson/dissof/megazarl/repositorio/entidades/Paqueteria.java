package edu.student.itson.dissof.megazarl.repositorio.entidades;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.modelos.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.repositorio.actualizaciones.ActualizacionPaqueteria;
import edu.student.itson.dissof.megazarl.repositorio.implementaciones.RepositorioPaqueteriaEnMemoria;

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

    public static PaqueteriaDTO recuperarPorId(Integer id) {
        return repositorio.recuperarPorId(id);
    }

    public static boolean existePorId(Integer id) {
        return repositorio.existePorId(id);
    }

    public static PaqueteriaDTO actualizar(Integer id, ActualizacionPaqueteria actualizacion) {
        return repositorio.actualizar(id, actualizacion);
    }

    public static void agregar(PaqueteriaDTO cliente) {
        repositorio.agregar(cliente);
    }

    public static void agregar(
            Integer id,
            String nombre,
            Float cobroKg,
            Float cobroHora,
            String direccionImagenPaqueteria
    ) {
        repositorio.agregar(
                new PaqueteriaDTO(
                        id,
                        nombre,
                        cobroKg,
                        cobroHora,
                        direccionImagenPaqueteria
                )
        );
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

    public static long cuenta() {
        return repositorio.cuenta();
    }

    public static boolean existe(Predicate<PaqueteriaDTO> criterio) {
        return repositorio.existe(criterio);
    }
}
