
package edu.student.itson.dissof.megazarl.objetosnegocio;
import edu.student.itson.dissof.megazal.datos.FabricaSubsistemasAccesoDatos;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProducto;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb.RepositorioProductoEnMongodb;
import java.util.Collection;
import java.util.List;


public class Producto {
    
    private static final RepositorioProducto repositorio;
    
    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().producto()) {
            case MEMORIA -> new RepositorioProductoEnMongodb(FabricaSubsistemasAccesoDatos.obtenerAdministradorMongodb());
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static ProductoDTONegocios recuperarPorId(IdProductoDTONegocios idProductoDTO) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException {
        
        return repositorio.recuperarPorId(idProductoDTO);
    }
    
    public static boolean existePorId(IdProductoDTONegocios idProductoDTO) 
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException {
        
        return repositorio.existePorId(idProductoDTO);
    }
     
    public static void agregar(ProductoDTONegocios productoDTONegocios) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException, 
            ValorParametroInvalidoNegocioException, 
            ParametroNuloNegocioException {
        
        repositorio.agregar(productoDTONegocios);
    }
    
    public static void agregar(Collection<ProductoDTONegocios> productosDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException {
        
        repositorio.agregar(productosDTONegocios);
    }
     
    public static List<ProductoDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
}
