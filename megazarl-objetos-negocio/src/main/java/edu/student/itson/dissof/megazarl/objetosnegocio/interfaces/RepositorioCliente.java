package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface RepositorioCliente {
    
    public abstract ClienteDTONegocios recuperarPorId(IdClienteDTONegocios idClieteDTO);

    public abstract boolean existePorId(IdClienteDTONegocios idClieteDTO);

    public abstract ClienteDTONegocios actualizar(ActualizacionClienteDTONegocios actualizacionClienteDTO);
    
    public abstract void agregar(ClienteDTONegocios clienteDTO);

    public abstract void agregar(Collection<ClienteDTONegocios> clientes);

    public abstract List<ClienteDTONegocios> recuperarTodos();

}
