
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.DireccionDTO;


public interface RepositorioDireccion extends RepositorioInmutable<DireccionDTO>{
    
    public abstract DireccionDTO recuperarPorId(IdDireccionDTO idDireccionDTO);

    public abstract boolean existePorId(IdDireccionDTO idClieteDTO);

    public abstract DireccionDTO actualizar(ActualizacionDireccionDTO actualizacionClienteDTO);
}
