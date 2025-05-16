
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProductoPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoPedidoDTO;


public interface RepositorioProductoPedido extends RepositorioInmutable<ProductoPedidoDTO>{
    
    public abstract ProductoPedidoDTO recuperarPorId(IdProductoPedidoDTO idProductoPedidoDTO);
    
    public abstract boolean existePorId(IdProductoPedidoDTO idProductoPedidoDTO);
}
