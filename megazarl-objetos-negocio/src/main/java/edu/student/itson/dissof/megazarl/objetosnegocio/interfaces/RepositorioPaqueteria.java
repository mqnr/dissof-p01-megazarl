package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTONegocios;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface RepositorioPaqueteria {
    
    public abstract PaqueteriaDTONegocios recuperarPorId(IdPaqueteriaDTONegocios idPaqueteriaDTO);

    public abstract boolean existePorId(IdPaqueteriaDTONegocios idPaqueteriaDTO);

    public abstract void agregar(PaqueteriaDTONegocios paqueteriaDTO);

    public abstract void agregar(Collection<PaqueteriaDTONegocios> paqueterias);

    public abstract List<PaqueteriaDTONegocios> recuperarTodos();

}
