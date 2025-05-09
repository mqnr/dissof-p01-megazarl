
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.ProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioProductoInventario;
import edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones.RepositorioProductoInventarioEnMemoria;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public class ProductoInventario {
    
    private static final RepositorioProductoInventario repositorio;
    
    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().productoInventario()) {
            case MEMORIA -> new RepositorioProductoInventarioEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static ProductoInventarioDTO recuperarPorId(IdProductoInventarioDTO idProductoInventarioDTO) {
        return repositorio.recuperarPorId(idProductoInventarioDTO);
    }
    
     public static boolean existePorId(IdProductoInventarioDTO idProductoInventarioDTO) {
        return repositorio.existePorId(idProductoInventarioDTO);
    }
     
    public static void agregar(ProductoInventarioDTO productoInventario) {
        repositorio.agregar(productoInventario);
    }
    
    public static void agregar(Collection<ProductoInventarioDTO> productosInventario) {
        repositorio.agregar(productosInventario);
    }
     
    public static List<ProductoInventarioDTO> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
    public static long cuenta() {
        return repositorio.cuenta();
    }
    
    public static boolean existe(Predicate<ProductoInventarioDTO> criterio) {
        return repositorio.existe(criterio);
    }
    
}
