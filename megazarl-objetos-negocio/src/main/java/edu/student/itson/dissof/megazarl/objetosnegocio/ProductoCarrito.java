
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioProductoCarrito;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioProductoCarritoEnMemoria;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public class ProductoCarrito {
    
    private static final RepositorioProductoCarrito repositorio;
    
    static {
            repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().productoCarrito()) {
            case MEMORIA -> new RepositorioProductoCarritoEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static ProductoCarritoDTO recuperarPorId(IdProductoCarritoDTO idProductoCarritoDTO) {
        return repositorio.recuperarPorId(idProductoCarritoDTO);
    }
    
    public static boolean existePorId(IdProductoCarritoDTO idProductoCarritoDTO) {
        return repositorio.existePorId(idProductoCarritoDTO);
    }
     
    public static ProductoCarritoDTO actualizar(ActualizacionProductoCarritoDTO actualizacionProductoCarritoDTO){
        return repositorio.actualizar(actualizacionProductoCarritoDTO);
    }
     
    public static void agregar(ProductoCarritoDTO productoCarrito) {
        repositorio.agregar(productoCarrito);
    }
    
    public static void agregar(Collection<ProductoCarritoDTO> productosCarrito) {
        repositorio.agregar(productosCarrito);
    }
    
    public static void removerPorId(IdProductoCarritoDTO idProductoCarritoDTO){
        repositorio.removerPorId(idProductoCarritoDTO);
    }
     
    public static List<ProductoCarritoDTO> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
    public static boolean existe(Predicate<ProductoCarritoDTO> criterio) {
        return repositorio.existe(criterio);
    }
}
