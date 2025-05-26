
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazal.datos.FabricaSubsistemasAccesoDatos;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioSucursal;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb.RepositorioSucursalEnMongodb;
import java.util.Collection;
import java.util.List;

public class Sucursal {
    private static final RepositorioSucursal repositorio;
    
    static {
            repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().sucursal()) {
            case MEMORIA -> new RepositorioSucursalEnMongodb(FabricaSubsistemasAccesoDatos.obtenerAdministradorMongodb());
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static SucursalDTONegocios recuperarPorId(IdSucursalDTONegocios idSucursalDTONegocios)
            throws FormatoIdInvalidoNegocioException, 
            ParametroNuloNegocioException, 
            RegistroInexistenteNegocioException {
        
        return repositorio.recuperarPorId(idSucursalDTONegocios);
    }
    
     public static boolean existePorId(IdSucursalDTONegocios idSucursalDTONegocios) 
             throws ParametroNuloNegocioException,
             FormatoIdInvalidoNegocioException {
         
        return repositorio.existePorId(idSucursalDTONegocios);
    }
     
    public static void agregar(SucursalDTONegocios sucursalDTONegocios)
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException, 
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        repositorio.agregar(sucursalDTONegocios);
    }

    public static void agregar(Collection<SucursalDTONegocios> sucursalesDTONegocios) 
            throws RegistroInexistenteNegocioException, 
            FormatoIdInvalidoNegocioException, 
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {

        repositorio.agregar(sucursalesDTONegocios);
    }
    
    public static List<SucursalDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
    public static SucursalDTONegocios obtenerSucursalMatriz() 
            throws RegistroInexistenteNegocioException{
        
        return repositorio.obtenerSucursalMatriz();
    }
    
}
