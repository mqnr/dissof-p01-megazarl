
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.infraestructura.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTO;


public interface RepositorioDireccion extends RepositorioInmutable<DireccionDTO>{
    
    public abstract DireccionDTO recuperarPorId(IdDireccionDTO idDireccionDTO);

    public abstract boolean existePorId(IdDireccionDTO idClieteDTO);

    public abstract DireccionDTO actualizar(ActualizacionDireccionDTO actualizacionClienteDTO);
}
