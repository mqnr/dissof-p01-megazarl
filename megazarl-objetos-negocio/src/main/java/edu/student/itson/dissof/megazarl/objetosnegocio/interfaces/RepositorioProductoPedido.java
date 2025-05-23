
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoPedidoDTONegocios;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public interface RepositorioProductoPedido {
    
    public abstract ProductoPedidoDTONegocios recuperarPorId(IdProductoPedidoDTONegocios idProductoPedidoDTO);
    
    public abstract boolean existePorId(IdProductoPedidoDTONegocios idProductoPedidoDTO);
    
    public abstract void agregar(ProductoPedidoDTONegocios productoPedido);
    
    public abstract void agregar(Collection<ProductoPedidoDTONegocios> productosPedido);
     
    public abstract List<ProductoPedidoDTONegocios> recuperarTodos();  
    
}
