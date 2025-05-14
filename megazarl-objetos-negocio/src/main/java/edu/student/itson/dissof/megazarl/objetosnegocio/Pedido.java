
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import static edu.student.itson.dissof.megazarl.configuracion.FuenteDatos.MEMORIA;
import edu.student.itson.dissof.megazarl.dto.infraestructura.PedidoDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioPedido;
import edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones.RepositorioPedidoEnMemoria;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
;

public class Pedido {
    
    private static final RepositorioPedido repositorio;
    
    static {
            repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().pedido()) {
            case MEMORIA -> new RepositorioPedidoEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static PedidoDTO recuperarPorId(Integer id) {
        return repositorio.recuperarPorId(id);
    }
    
     public static boolean existePorId(Integer id) {
        return repositorio.existePorId(id);
    }
     
    public static void agregar(PedidoDTO pedido) {
        repositorio.agregar(pedido);
    }
    
    public static void agregar(Collection<PedidoDTO> pedidos) {
        repositorio.agregar(pedidos);
    }
     
    public static List<PedidoDTO> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
    public static long cuenta() {
        return repositorio.cuenta();
    }
    
    public static boolean existe(Predicate<PedidoDTO> criterio) {
        return repositorio.existe(criterio);
    }
}
