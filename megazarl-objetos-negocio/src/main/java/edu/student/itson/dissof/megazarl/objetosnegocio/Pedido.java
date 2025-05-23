
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import static edu.student.itson.dissof.megazarl.configuracion.FuenteDatos.MEMORIA;
import edu.student.itson.dissof.megazarl.dto.negocios.PedidoDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioPedido;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioPedidoEnMemoria;
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
    
    public static PedidoDTONegocios recuperarPorId(Integer id) {
        return repositorio.recuperarPorId(id);
    }
    
    public static boolean existePorId(Integer id) {
        return repositorio.existePorId(id);
    }
     
    public static void agregar(PedidoDTONegocios pedido) {
        repositorio.agregar(pedido);
    }
    
    public static void agregar(Collection<PedidoDTONegocios> pedidos) {
        repositorio.agregar(pedidos);
    }
     
    public static List<PedidoDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
}
