
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTONegocios;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public interface RepositorioProducto {
    
    public abstract ProductoDTONegocios recuperarPorId(IdProductoDTONegocios idProductoDTO);
    
    public abstract boolean existePorId(IdProductoDTONegocios idProductoDTO);

    public abstract void agregar(ProductoDTONegocios producto);
    
    public abstract void agregar(Collection<ProductoDTONegocios> productos);
     
    public abstract List<ProductoDTONegocios> recuperarTodos();
    
}
