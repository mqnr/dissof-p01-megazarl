package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTO;

public interface RepositorioCliente extends RepositorioInmutable<ClienteDTO> {
    
    public abstract ClienteDTO recuperarPorId(IdClienteDTO idClieteDTO);

    public abstract boolean existePorId(IdClienteDTO idClieteDTO);

    public abstract ClienteDTO actualizar(ActualizacionClienteDTO actualizacionClienteDTO);
}
