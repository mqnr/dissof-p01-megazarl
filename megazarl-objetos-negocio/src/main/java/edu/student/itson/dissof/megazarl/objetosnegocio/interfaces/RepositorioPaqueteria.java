package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import java.util.Collection;
import java.util.List;

public interface RepositorioPaqueteria {
    
    public abstract PaqueteriaDTONegocios recuperarPorId(IdPaqueteriaDTONegocios idPaquteriaDTONegocios)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException;
    
    public abstract boolean existePorId(IdPaqueteriaDTONegocios idPaqueteriaDTONegocios)
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException;
    
    public abstract void agregar(PaqueteriaDTONegocios paqueteriaDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException;
    
    public abstract void agregar(Collection<PaqueteriaDTONegocios> paqueteriasDTONegocios)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException;

    public abstract List<PaqueteriaDTONegocios> recuperarTodos();
}
