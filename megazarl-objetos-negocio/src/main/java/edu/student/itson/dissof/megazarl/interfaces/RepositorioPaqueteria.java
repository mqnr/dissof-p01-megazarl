package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.infraestructura.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdPaqueteriaDTO;

public interface RepositorioPaqueteria extends RepositorioInmutable<PaqueteriaDTO> {
    
    public abstract PaqueteriaDTO recuperarPorId(IdPaqueteriaDTO idPaqueteriaDTO);

    public abstract boolean existePorId(IdPaqueteriaDTO idPaqueteriaDTO);

}
