package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import java.util.Collection;
import java.util.List;

public interface RepositorioCliente {
    
    public abstract ClienteDTONegocios recuperarPorId(IdClienteDTONegocios idClieteDTO) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException;

    public abstract boolean existePorId(IdClienteDTONegocios idClieteDTO)
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException;

    public abstract ClienteDTONegocios actualizar(ActualizacionClienteDTONegocios actualizacionClienteDTO) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException;
    
    public abstract void agregar(ClienteDTONegocios clienteDTO)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException;

    public abstract void agregar(Collection<ClienteDTONegocios> clientes)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException;

    public abstract List<ClienteDTONegocios> recuperarTodos();

}
