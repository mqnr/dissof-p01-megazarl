
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTO;


public interface RepositorioProductoInventario extends RepositorioInmutable<ProductoInventarioDTO> {
    
    public abstract ProductoInventarioDTO recuperarPorId(IdProductoInventarioDTO idProductoInventarioDTO);
    
    public abstract boolean existePorId(IdProductoInventarioDTO idProductoInventarioDTO);
    
    public ProductoInventarioDTO actualizar(ActualizacionProductoInventarioDTO actualizacionProductoInventarioDTO);
    
}
