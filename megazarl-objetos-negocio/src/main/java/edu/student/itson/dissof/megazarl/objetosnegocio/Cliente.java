package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazal.datos.FabricaSubsistemasAccesoDatos;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioCliente;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb.RepositorioClienteEnMongodb;

import java.util.Collection;
import java.util.List;


public class Cliente {
    private static final RepositorioCliente repositorio;

    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().cliente()) {
            case MEMORIA -> new RepositorioClienteEnMongodb(FabricaSubsistemasAccesoDatos.obtenerAdministradorMongodb());
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static ClienteDTONegocios recuperarPorId(IdClienteDTONegocios idClienteDTO) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException {
        
        return repositorio.recuperarPorId(idClienteDTO);
    }

    public static boolean existePorId(IdClienteDTONegocios idClienteDTO) 
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException {
        
        return repositorio.existePorId(idClienteDTO);
    }

    public static ClienteDTONegocios actualizar(ActualizacionClienteDTONegocios actualizacionClienteDTO) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        return repositorio.actualizar(actualizacionClienteDTO);
    }

    public static void agregar(ClienteDTONegocios clienteDTO)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException, 
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        repositorio.agregar(clienteDTO);
    }

    public static void agregar(Collection<ClienteDTONegocios> clientes) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        repositorio.agregar(clientes);
    }

    public static List<ClienteDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
}
