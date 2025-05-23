
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTONegocios;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public interface RepositorioProductoInventario {
    
    public abstract ProductoInventarioDTONegocios recuperarPorId(IdProductoInventarioDTONegocios idProductoInventarioDTO);
    
    public abstract boolean existePorId(IdProductoInventarioDTONegocios idProductoInventarioDTO);
    
    public ProductoInventarioDTONegocios actualizar(ActualizacionProductoInventarioDTONegocios actualizacionProductoInventarioDTO);
    
    public abstract void agregar(ProductoInventarioDTONegocios productoInventario);
    
    public abstract void agregar(Collection<ProductoInventarioDTONegocios> productosInventario);
    
    public abstract List<ProductoInventarioDTONegocios> recuperarTodos();
 
}
