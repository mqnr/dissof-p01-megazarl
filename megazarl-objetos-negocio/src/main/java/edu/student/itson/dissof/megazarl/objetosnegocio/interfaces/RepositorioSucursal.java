
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public interface RepositorioSucursal {
    
    public abstract SucursalDTONegocios recuperarPorId(IdSucursalDTONegocios idSucursalDTO)
            throws FormatoIdInvalidoNegocioException,
            ParametroNuloNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException;
    
    public abstract SucursalDTONegocios obtenerSucursalMatriz() throws RegistroInexistenteNegocioException;
    
    public abstract boolean existePorId(IdSucursalDTONegocios idSucursalDTO) 
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException;
    
    public abstract void agregar(SucursalDTONegocios sucursal)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException;

    public abstract void agregar(Collection<SucursalDTONegocios> sucursales)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException;
     
    public abstract List<SucursalDTONegocios> recuperarTodos();
    
}
