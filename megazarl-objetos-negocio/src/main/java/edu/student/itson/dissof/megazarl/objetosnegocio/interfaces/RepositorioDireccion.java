
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTONegocios;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public interface RepositorioDireccion{
    
    public abstract DireccionDTONegocios recuperarPorId(IdDireccionDTONegocios idDireccionDTO);

    public abstract boolean existePorId(IdDireccionDTONegocios idClieteDTO);

    public abstract DireccionDTONegocios actualizar(ActualizacionDireccionDTONegocios actualizacionClienteDTO);
    
    public abstract void agregar(DireccionDTONegocios direccion);

    public abstract void agregar(Collection<DireccionDTONegocios> direcciones);

    public abstract List<DireccionDTONegocios> recuperarTodos();

}
