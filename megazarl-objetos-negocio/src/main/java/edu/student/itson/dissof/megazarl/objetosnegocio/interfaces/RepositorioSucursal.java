
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTONegocios;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public interface RepositorioSucursal {
    
    public abstract SucursalDTONegocios recuperarPorId(IdSucursalDTONegocios idSucursalDTO);
    
    public abstract SucursalDTONegocios obtenerSucursalMatriz();
    
    public abstract boolean existePorId(IdSucursalDTONegocios idSucursalDTO);
    
    public abstract void agregar(SucursalDTONegocios sucursal);

    public abstract void agregar(Collection<SucursalDTONegocios> sucursales);
     
    public abstract List<SucursalDTONegocios> recuperarTodos();
    
}
