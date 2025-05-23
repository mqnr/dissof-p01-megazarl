
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProductoInventario;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioProductoInventarioEnMemoria;
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
    
    public static ProductoInventarioDTONegocios recuperarPorId(IdProductoInventarioDTONegocios idProductoInventarioDTO) {
        return repositorio.recuperarPorId(idProductoInventarioDTO);
    }
    
     public static boolean existePorId(IdProductoInventarioDTONegocios idProductoInventarioDTO) {
        return repositorio.existePorId(idProductoInventarioDTO);
    }
     
    public static void agregar(ProductoInventarioDTONegocios productoInventario) {
        repositorio.agregar(productoInventario);
    }
    
    public static void agregar(Collection<ProductoInventarioDTONegocios> productosInventario) {
        repositorio.agregar(productosInventario);
    }
    
    public static ProductoInventarioDTONegocios actualizar(ActualizacionProductoInventarioDTONegocios actualizacionProductoInventarioDTO){
        return repositorio.actualizar(actualizacionProductoInventarioDTO);
    }
     
    public static List<ProductoInventarioDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
}
