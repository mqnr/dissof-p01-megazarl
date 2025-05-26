
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazal.datos.FabricaSubsistemasAccesoDatos;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProveedor;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb.RepositorioProveedorEnMongodb;
import java.util.Collection;
import java.util.List;

public class Proveedor {
    private static final RepositorioProveedor repositorio;
    
    static {
            repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().proveedor()) {
            case MEMORIA -> new RepositorioProveedorEnMongodb(FabricaSubsistemasAccesoDatos.obtenerAdministradorMongodb());
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static ProveedorDTONegocios recuperarPorId(IdProveedorDTONegocios idProveedorDTO)
            throws FormatoIdInvalidoNegocioException, 
            ParametroNuloNegocioException,
            RegistroInexistenteNegocioException {
        
        return repositorio.recuperarPorId(idProveedorDTO);
    }
    
     public static boolean existePorId(IdProveedorDTONegocios idProveedorDTONegocios) 
             throws ParametroNuloNegocioException,
             FormatoIdInvalidoNegocioException {
         
        return repositorio.existePorId(idProveedorDTONegocios);
    }
     
    public static void agregar(ProveedorDTONegocios proveedorDTONegocios)
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException, 
            ValorParametroInvalidoNegocioException, 
            ParametroNuloNegocioException {
        
        repositorio.agregar(proveedorDTONegocios);
    }
    
    public static void agregar(Collection<ProveedorDTONegocios> proveedoresDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException, 
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        repositorio.agregar(proveedoresDTONegocios);
    }
     
    public static List<ProveedorDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
}
