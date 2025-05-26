
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazal.datos.FabricaSubsistemasAccesoDatos;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import static edu.student.itson.dissof.megazarl.configuracion.FuenteDatos.MEMORIA;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.PedidoDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioPedido;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb.RepositorioPedidoEnMongodb;
import java.util.Collection;
import java.util.List;

public class Pedido {
    
    private static final RepositorioPedido repositorio;
    
    static {
            repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().pedido()) {
            case MEMORIA -> new RepositorioPedidoEnMongodb(FabricaSubsistemasAccesoDatos.obtenerAdministradorMongodb());
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static PedidoDTONegocios recuperarPorId(IdPedidoDTONegocios idPedidoDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException {
        
        return repositorio.recuperarPorId(idPedidoDTONegocios);
    }
    
    public static boolean existePorId(IdPedidoDTONegocios idPedidoDTONegocios) 
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException {
        
        return repositorio.existePorId(idPedidoDTONegocios);
    }
     
    public static void agregar(PedidoDTONegocios pedidoDTONegocios)
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException {
        
        repositorio.agregar(pedidoDTONegocios);
    }
    
    public static void agregar(Collection<PedidoDTONegocios> pedidosDTONegocios)
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException {
        
        repositorio.agregar(pedidosDTONegocios);
    }
     
    public static List<PedidoDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
}
