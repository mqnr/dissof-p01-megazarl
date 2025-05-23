
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCarritoDTONegocios;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public interface RepositorioProductoCarrito {
    
    public abstract ProductoCarritoDTONegocios recuperarPorId(IdProductoCarritoDTONegocios idProductoCarritoDTO);
    
    public abstract boolean existePorId(IdProductoCarritoDTONegocios idProductoCarritoDTO);
    
    public abstract ProductoCarritoDTONegocios actualizar(ActualizacionProductoCarritoDTONegocios actualizacionProductoCarritoDTO);
    
    public abstract void removerPorId(IdProductoCarritoDTONegocios idProductoCarritoDTO);
    
    public abstract void agregar(ProductoCarritoDTONegocios productoCarrito);
    
    public abstract void agregar(Collection<ProductoCarritoDTONegocios> productosCarrito);
     
    public abstract List<ProductoCarritoDTONegocios> recuperarTodos();
    
}
