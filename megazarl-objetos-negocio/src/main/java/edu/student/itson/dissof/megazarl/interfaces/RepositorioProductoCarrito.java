
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCarritoDTO;


public interface RepositorioProductoCarrito extends RepositorioInmutable<ProductoCarritoDTO> {
    
    public abstract ProductoCarritoDTO recuperarPorId(IdProductoCarritoDTO idProductoCarritoDTO);
    
    public abstract boolean existePorId(IdProductoCarritoDTO idProductoCarritoDTO);
    
    public abstract ProductoCarritoDTO actualizar(ActualizacionProductoCarritoDTO actualizacionProductoCarritoDTO);
    
    public abstract void removerPorId(IdProductoCarritoDTO idProductoCarritoDTO);
    
}
