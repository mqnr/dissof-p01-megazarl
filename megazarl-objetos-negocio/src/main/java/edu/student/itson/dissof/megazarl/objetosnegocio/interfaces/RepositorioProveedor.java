
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public interface RepositorioProveedor {
    
    public abstract ProveedorDTONegocios recuperarPorId(IdProveedorDTONegocios idProveedorDTO)
            throws FormatoIdInvalidoNegocioException,
            ParametroNuloNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException;

    public abstract boolean existePorId(IdProveedorDTONegocios idProveedorDTO)
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException;
    
    public abstract void agregar(ProveedorDTONegocios proveedorDTO)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException;
    
    public abstract void agregar(Collection<ProveedorDTONegocios> proveedores)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException;
     
    public abstract List<ProveedorDTONegocios> recuperarTodos();

}
