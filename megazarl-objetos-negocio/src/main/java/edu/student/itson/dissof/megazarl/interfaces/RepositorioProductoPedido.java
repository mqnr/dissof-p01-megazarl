
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoPedidoDTO;


public interface RepositorioProductoPedido extends RepositorioInmutable<ProductoPedidoDTO>{
    
    public abstract ProductoPedidoDTO recuperarPorId(IdProductoPedidoDTO idProductoPedidoDTO);
    
    public abstract boolean existePorId(IdProductoPedidoDTO idProductoPedidoDTO);
}
