
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.PedidoDTONegocios;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public interface RepositorioPedido{
    
    public abstract PedidoDTONegocios recuperarPorId(Integer id);
    
    public abstract boolean existePorId(Integer id);
    
    public abstract void agregar(PedidoDTONegocios pedido);
    
    public abstract void agregar(Collection<PedidoDTONegocios> pedidos);
     
    public abstract List<PedidoDTONegocios> recuperarTodos();

}
