
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoOrdenCompraDTO;
import java.util.List;


public interface RepositorioProducto extends RepositorioInmutable<ProductoDTO> {
    
    public abstract ProductoDTO recuperarPorId(IdProductoDTO idProductoDTO);
    
    public abstract boolean existePorId(IdProductoDTO idProductoDTO);
    
}
