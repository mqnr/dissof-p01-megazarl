package edu.student.itson.dissof.megazarl.repositorio.entidades;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.modelos.ClienteDTO;
import edu.student.itson.dissof.megazarl.repositorio.actualizaciones.ActualizacionCliente;
import edu.student.itson.dissof.megazarl.repositorio.implementaciones.RepositorioClienteEnMemoria;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class Cliente {
    private static final RepositorioCliente repositorio;

    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().cliente()) {
            case MEMORIA -> new RepositorioClienteEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }

    public static ClienteDTO buscarPorId(Integer id) {
        return repositorio.buscarPorId(id);
    }

    public static boolean existePorId(Integer id) {
        return repositorio.existePorId(id);
    }

    public static ClienteDTO actualizar(Integer id, ActualizacionCliente actualizacion) {
        return repositorio.actualizar(id, actualizacion);
    }

    public static void guardar(ClienteDTO cliente) {
        repositorio.guardar(cliente);
    }

    public static void guardar(
            Integer id,
            String nombres,
            String apellidoPaterno,
            String apellidoMaterno,
            String codigoPostalEnvio,
            String calleEnvio,
            String numeroDomicilioEnvio
    ) {
        repositorio.guardar(
                new ClienteDTO(
                        id,
                        nombres,
                        apellidoPaterno,
                        apellidoMaterno,
                        codigoPostalEnvio,
                        calleEnvio,
                        numeroDomicilioEnvio
                )
        );
    }

    public static void guardarMuchos(Collection<ClienteDTO> clientes) {
        repositorio.guardarMuchos(clientes);
    }

    public static List<ClienteDTO> encontrarTodos() {
        return repositorio.encontrarTodos();
    }

    public static long cuenta() {
        return repositorio.cuenta();
    }

    public static boolean existe(Predicate<ClienteDTO> criterio) {
        return repositorio.existe(criterio);
    }
}
