
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;


import edu.student.itson.dissof.megazarl.dto.negocios.PedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioPedido;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RepositorioPedidoEnMemoria implements RepositorioPedido{
    
    private final List<PedidoDTONegocios> listaPedidos;
    
    private static Long ID_ACTUAL_PEDIDO = 1L;
    
    
    public RepositorioPedidoEnMemoria() {
        listaPedidos = new ArrayList<>();
    }
    
    public RepositorioPedidoEnMemoria(Collection<PedidoDTONegocios> pedidos) {
        listaPedidos = new ArrayList<>(pedidos);
    }
    
    @Override
    public PedidoDTONegocios recuperarPorId(Integer id) {
        return listaPedidos.stream()
                .filter(pedido -> pedido.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(Integer id) {
        
        return listaPedidos.stream().anyMatch(pedido -> pedido.getId().equals(id));

    }

    @Override
    public void agregar(PedidoDTONegocios pedido) {
        if(pedido.getId() == null){
            pedido.setId(new IdEntidadGenericoNegocios(ID_ACTUAL_PEDIDO++));
        }
        listaPedidos.add(pedido);
    }

    @Override
    public void agregar(Collection<PedidoDTONegocios> pedidos) {
        for(PedidoDTONegocios pedido: pedidos){
            if(pedido.getId() == null){
                pedido.setId(new IdEntidadGenericoNegocios(ID_ACTUAL_PEDIDO++));
            }
        }
        listaPedidos.addAll(pedidos);
    }

    @Override
    public List<PedidoDTONegocios> recuperarTodos() {
        return new ArrayList<>(listaPedidos);
    }
    
}
