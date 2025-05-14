package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdClienteDTO;

public interface RepositorioCliente extends RepositorioInmutable<ClienteDTO> {
    
    public abstract ClienteDTO recuperarPorId(IdClienteDTO idClieteDTO);

    public abstract boolean existePorId(IdClienteDTO idClieteDTO);

    public abstract ClienteDTO actualizar(ActualizacionClienteDTO actualizacionClienteDTO);
}
