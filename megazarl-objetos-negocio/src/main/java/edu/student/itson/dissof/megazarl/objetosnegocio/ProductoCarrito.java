
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProductoCarrito;
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
    
    public static ProductoCarritoDTONegocios recuperarPorId(IdProductoCarritoDTONegocios idProductoCarritoDTO) {
        return repositorio.recuperarPorId(idProductoCarritoDTO);
    }
    
    public static boolean existePorId(IdProductoCarritoDTONegocios idProductoCarritoDTO) {
        return repositorio.existePorId(idProductoCarritoDTO);
    }
     
    public static ProductoCarritoDTONegocios actualizar(ActualizacionProductoCarritoDTONegocios actualizacionProductoCarritoDTO){
        return repositorio.actualizar(actualizacionProductoCarritoDTO);
    }
     
    public static void agregar(ProductoCarritoDTONegocios productoCarrito) {
        repositorio.agregar(productoCarrito);
    }
    
    public static void agregar(Collection<ProductoCarritoDTONegocios> productosCarrito) {
        repositorio.agregar(productosCarrito);
    }
    
    public static void removerPorId(IdProductoCarritoDTONegocios idProductoCarritoDTO){
        repositorio.removerPorId(idProductoCarritoDTO);
    }
     
    public static List<ProductoCarritoDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
}
