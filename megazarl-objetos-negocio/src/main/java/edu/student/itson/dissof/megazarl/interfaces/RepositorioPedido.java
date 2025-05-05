
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.PedidoDTO;


public interface RepositorioPedido extends RepositorioInmutable<PedidoDTO>{
    
    public abstract PedidoDTO recuperarPorId(Integer id);
    
    public abstract boolean existePorId(Integer id);
    
}
