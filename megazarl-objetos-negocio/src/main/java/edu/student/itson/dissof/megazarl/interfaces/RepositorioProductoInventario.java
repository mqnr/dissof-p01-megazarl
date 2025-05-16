
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProductoInventarioDTO;


public interface RepositorioProductoInventario extends RepositorioInmutable<ProductoInventarioDTO> {
    
    public abstract ProductoInventarioDTO recuperarPorId(IdProductoInventarioDTO idProductoInventarioDTO);
    
    public abstract boolean existePorId(IdProductoInventarioDTO idProductoInventarioDTO);
    
    public ProductoInventarioDTO actualizar(ActualizacionProductoInventarioDTO actualizacionProductoInventarioDTO);
    
}
