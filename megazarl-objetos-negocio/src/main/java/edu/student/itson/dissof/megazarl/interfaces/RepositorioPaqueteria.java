package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.PaqueteriaDTO;

public interface RepositorioPaqueteria extends RepositorioInmutable<PaqueteriaDTO> {
    
    public abstract PaqueteriaDTO recuperarPorId(IdPaqueteriaDTO idPaqueteriaDTO);

    public abstract boolean existePorId(IdPaqueteriaDTO idPaqueteriaDTO);

}
