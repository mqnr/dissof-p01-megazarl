package edu.student.itson.dissof.megazarl.repositorio.entidades;

import edu.student.itson.dissof.megazarl.dto.modelos.ClienteDTO;
import edu.student.itson.dissof.megazarl.repositorio.RepositorioInmutable;
import edu.student.itson.dissof.megazarl.repositorio.actualizaciones.ActualizacionCliente;

public interface RepositorioCliente extends RepositorioInmutable<ClienteDTO> {
    ClienteDTO buscarPorId(Integer id);

    boolean existePorId(Integer id);

    ClienteDTO actualizar(Integer id, ActualizacionCliente actualizacion);
}
