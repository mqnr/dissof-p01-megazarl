
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public interface RepositorioProveedor {
    
    public abstract ProveedorDTONegocios recuperarPorId(IdProveedorDTONegocios idProveedorDTO);

    public abstract boolean existePorId(IdProveedorDTONegocios idProveedorDTO);
    
    public abstract void agregar(ProveedorDTONegocios proveedorDTO);
    
    public abstract void agregar(Collection<ProveedorDTONegocios> proveedores);
     
    public abstract List<ProveedorDTONegocios> recuperarTodos();

}
