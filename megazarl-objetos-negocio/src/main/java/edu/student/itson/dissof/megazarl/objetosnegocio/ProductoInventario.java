
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazal.datos.FabricaSubsistemasAccesoDatos;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProductoInventario;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb.RepositorioProductoInventarioEnMongodb;
import java.util.Collection;
import java.util.List;


public class ProductoInventario {
    
    private static final RepositorioProductoInventario repositorio;
    
    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().productoInventario()) {
            case MEMORIA -> new RepositorioProductoInventarioEnMongodb(FabricaSubsistemasAccesoDatos.obtenerAdministradorMongodb());
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static ProductoInventarioDTONegocios recuperarPorId(IdProductoInventarioDTONegocios idProductoInventarioDTO) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException {
        
        return repositorio.recuperarPorId(idProductoInventarioDTO);
    }
    
     public static boolean existePorId(IdProductoInventarioDTONegocios idProductoInventarioDTO) 
             throws ParametroNuloNegocioException,
             FormatoIdInvalidoNegocioException {
         
        return repositorio.existePorId(idProductoInventarioDTO);
    }
     
     public static ProductoInventarioDTONegocios actualizar(ActualizacionProductoInventarioDTONegocios actualizacionProductoInventarioDTONegocios)
             throws FormatoIdInvalidoNegocioException, 
             RegistroInexistenteNegocioException,
             ParametroNuloNegocioException{
         
         return repositorio.actualizar(actualizacionProductoInventarioDTONegocios);
     }
     
    public static void agregar(ProductoInventarioDTONegocios productoInventario) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException {
        
        repositorio.agregar(productoInventario);
    }
    
    public static void agregar(Collection<ProductoInventarioDTONegocios> productosInventario) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException {
        
        repositorio.agregar(productosInventario);
    }
    
    public static List<ProductoInventarioDTONegocios> recuperarPorIdProducto(IdProductoDTONegocios idProductoDTONegocios) 
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException {
        
        return repositorio.recuperarPorIdProducto(idProductoDTONegocios);
    }
     
    public static List<ProductoInventarioDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
}
