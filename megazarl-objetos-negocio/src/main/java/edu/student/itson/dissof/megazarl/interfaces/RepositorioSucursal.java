
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.SucursalDTO;


public interface RepositorioSucursal extends RepositorioInmutable<SucursalDTO>{
    
    public abstract SucursalDTO recuperarPorId(IdSucursalDTO idSucursalDTO);
    
    public abstract SucursalDTO obtenerSucursalMatriz();
    
    public abstract boolean existePorId(IdSucursalDTO idSucursalDTO);
    
}
