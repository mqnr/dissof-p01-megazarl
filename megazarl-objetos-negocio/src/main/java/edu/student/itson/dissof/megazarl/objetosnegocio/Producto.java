
package edu.student.itson.dissof.megazarl.objetosnegocio;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProductoDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioProducto;
import edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones.RepositorioProductoEnMemoria;
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
    
    public static ProductoDTO recuperarPorId(IdProductoDTO idProductoDTO) {
        return repositorio.recuperarPorId(idProductoDTO);
    }
    
     public static boolean existePorId(IdProductoDTO idProductoDTO) {
        return repositorio.existePorId(idProductoDTO);
    }
     
    public static void agregar(ProductoDTO producto) {
        repositorio.agregar(producto);
    }
    
    public static void agregar(Collection<ProductoDTO> productos) {
        repositorio.agregar(productos);
    }
     
    public static List<ProductoDTO> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
    public static long cuenta() {
        return repositorio.cuenta();
    }
    
    public static boolean existe(Predicate<ProductoDTO> criterio) {
        return repositorio.existe(criterio);
    }

}
