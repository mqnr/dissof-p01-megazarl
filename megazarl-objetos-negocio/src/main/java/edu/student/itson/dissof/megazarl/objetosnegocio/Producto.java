
package edu.student.itson.dissof.megazarl.objetosnegocio;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProducto;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioProductoEnMemoria;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public class Producto {
    
    private static final RepositorioProducto repositorio;
    
    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().producto()) {
            case MEMORIA -> new RepositorioProductoEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static ProductoDTONegocios recuperarPorId(IdProductoDTONegocios idProductoDTO) {
        return repositorio.recuperarPorId(idProductoDTO);
    }
    
    public static boolean existePorId(IdProductoDTONegocios idProductoDTO) {
        return repositorio.existePorId(idProductoDTO);
    }
     
    public static void agregar(ProductoDTONegocios producto) {
        repositorio.agregar(producto);
    }
    
    public static void agregar(Collection<ProductoDTONegocios> productos) {
        repositorio.agregar(productos);
    }
     
    public static List<ProductoDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
}
