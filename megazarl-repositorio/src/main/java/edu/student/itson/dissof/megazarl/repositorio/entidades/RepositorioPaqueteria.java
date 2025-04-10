package edu.student.itson.dissof.megazarl.repositorio.entidades;

import edu.student.itson.dissof.megazarl.dto.modelos.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.repositorio.RepositorioInmutable;
import edu.student.itson.dissof.megazarl.repositorio.actualizaciones.ActualizacionPaqueteria;

public interface RepositorioPaqueteria extends RepositorioInmutable<PaqueteriaDTO> {
    PaqueteriaDTO buscarPorId(Integer id);

    boolean existePorId(Integer id);

    PaqueteriaDTO actualizar(Integer id, ActualizacionPaqueteria actualizacion);
}
