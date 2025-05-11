
package edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones;


import edu.student.itson.dissof.megazarl.dto.infraestructura.PedidoDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioPedido;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RepositorioPedidoEnMemoria implements RepositorioPedido{
    
    private final List<PedidoDTO> listaPedidos;
    private Long ID_ACTUAL_PEDIDO = 1L;
    
    
    public RepositorioPedidoEnMemoria() {
        listaPedidos = new ArrayList<>();
    }
    
    public RepositorioPedidoEnMemoria(Collection<PedidoDTO> pedidos) {
        listaPedidos = new ArrayList<>(pedidos);
    }
    
    @Override
    public PedidoDTO recuperarPorId(Integer id) {
        return listaPedidos.stream()
                .filter(pedido -> pedido.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(Integer id) {
        return existe(pedido -> pedido.getId().equals(id));
    }

    @Override
    public Stream<PedidoDTO> stream() {
        return listaPedidos.stream();
    }

    @Override
    public void agregar(PedidoDTO pedido) {
        if(pedido.getId() == null){
            pedido.setId(ID_ACTUAL_PEDIDO++);
        }
        listaPedidos.add(pedido);
    }

    @Override
    public void agregar(Collection<PedidoDTO> pedidos) {
        for(PedidoDTO pedido: pedidos){
            if(pedido.getId() == null){
                pedido.setId(ID_ACTUAL_PEDIDO++);
            }
        }
        listaPedidos.addAll(pedidos);
    }

    @Override
    public List<PedidoDTO> recuperarTodos() {
        return new ArrayList<>(listaPedidos);
    }

    @Override
    public long cuenta() {
        return listaPedidos.size();
    }

    @Override
    public boolean existe(Predicate<PedidoDTO> criterio) {
        return listaPedidos.stream().anyMatch(criterio);
    }
    
}
