
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.infraestructura.ProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTO;


public interface RepositorioProveedor extends RepositorioInmutable<ProveedorDTO>{
    
    public abstract ProveedorDTO recuperarPorId(IdProveedorDTO idProveedorDTO);

    public abstract boolean existePorId(IdProveedorDTO idProveedorDTO);
    
}
