
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.ProductoInventarioDTO;


public interface RepositorioProductoInventario extends RepositorioInmutable<ProductoInventarioDTO> {
    
    public abstract ProductoInventarioDTO recuperarPorId(IdProductoInventarioDTO idProductoInventarioDTO);
    
    public abstract boolean existePorId(IdProductoInventarioDTO idProductoInventarioDTO);
    
}
