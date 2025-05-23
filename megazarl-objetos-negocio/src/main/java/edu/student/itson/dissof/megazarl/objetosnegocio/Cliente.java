package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioCliente;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioClienteEnMemoria;

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
    
    public static ClienteDTONegocios recuperarPorId(IdClienteDTONegocios idClienteDTO) {
        return repositorio.recuperarPorId(idClienteDTO);
    }

    public static boolean existePorId(IdClienteDTONegocios idClienteDTO) {
        return repositorio.existePorId(idClienteDTO);
    }

    public static ClienteDTONegocios actualizar(ActualizacionClienteDTONegocios actualizacionClienteDTO) {
        return repositorio.actualizar(actualizacionClienteDTO);
    }

    public static void agregar(ClienteDTONegocios clienteDTO) {
        repositorio.agregar(clienteDTO);
    }

    public static void agregar(Collection<ClienteDTONegocios> clientes) {
        repositorio.agregar(clientes);
    }

    public static List<ClienteDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
}
