package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioCliente;
import edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones.RepositorioClienteEnMemoria;

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

    public static ClienteDTO recuperarPorId(IdClienteDTO idClienteDTO) {
        return repositorio.recuperarPorId(idClienteDTO);
    }

    public static boolean existePorId(IdClienteDTO idClienteDTO) {
        return repositorio.existePorId(idClienteDTO);
    }

    public static ClienteDTO actualizar(ActualizacionClienteDTO actualizacionClienteDTO) {
        return repositorio.actualizar(actualizacionClienteDTO);
    }

    public static void agregar(ClienteDTO clienteDTO) {
        repositorio.agregar(clienteDTO);
    }

    public static void agregar(Collection<ClienteDTO> clientes) {
        repositorio.agregar(clientes);
    }

    public static List<ClienteDTO> recuperarTodos() {
        return repositorio.recuperarTodos();
    }

    public static long cuenta() {
        return repositorio.cuenta();
    }

    public static boolean existe(Predicate<ClienteDTO> criterio) {
        return repositorio.existe(criterio);
    }
}
