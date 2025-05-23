
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProductoPedido;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioProductoPedidoEnMemoria;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public class ProductoPedido {
    private static final RepositorioProductoPedido repositorio;
    
    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().productoPedido()) {
            case MEMORIA -> new RepositorioProductoPedidoEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static ProductoPedidoDTONegocios recuperarPorId(IdProductoPedidoDTONegocios idProductoPedidoDTO) {
        return repositorio.recuperarPorId(idProductoPedidoDTO);
    }
    
     public static boolean existePorId(IdProductoPedidoDTONegocios idProductoPedidoDTO) {
        return repositorio.existePorId(idProductoPedidoDTO);
    }
     
    public static void agregar(ProductoPedidoDTONegocios productoPedido) {
        repositorio.agregar(productoPedido);
    }
    
    public static void agregar(Collection<ProductoPedidoDTONegocios> productosPedido) {
        repositorio.agregar(productosPedido);
    }
     
    public static List<ProductoPedidoDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
}
