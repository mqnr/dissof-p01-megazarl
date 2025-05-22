
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoPedidoDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioProductoPedido;
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
    
    public static ProductoPedidoDTO recuperarPorId(IdProductoPedidoDTO idProductoPedidoDTO) {
        return repositorio.recuperarPorId(idProductoPedidoDTO);
    }
    
     public static boolean existePorId(IdProductoPedidoDTO idProductoPedidoDTO) {
        return repositorio.existePorId(idProductoPedidoDTO);
    }
     
    public static void agregar(ProductoPedidoDTO productoPedido) {
        repositorio.agregar(productoPedido);
    }
    
    public static void agregar(Collection<ProductoPedidoDTO> productosPedido) {
        repositorio.agregar(productosPedido);
    }
     
    public static List<ProductoPedidoDTO> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
    
    public static boolean existe(Predicate<ProductoPedidoDTO> criterio) {
        return repositorio.existe(criterio);
    }
}
