
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.AuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdAuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import java.util.Collection;
import java.util.List;

/**
 * RepositorioAuxiliarVentas.java
 * 
 * Interfaz que debe implementar cualquier repositorio de un objeto de negocio.
 * 
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public interface RepositorioAuxiliarVentas{
    
    public abstract AuxiliarVentasDTONegocios recuperarPorId(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTO)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException;
    
    public abstract boolean existePorId(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTO)
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException;
    
    public abstract void agregar(AuxiliarVentasDTONegocios auxiliarVentas)
            throws FormatoIdInvalidoNegocioException,
            FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException ;
    
    public abstract void agregar(Collection<AuxiliarVentasDTONegocios> auxiliaresVentas) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException;
     
    public abstract List<AuxiliarVentasDTONegocios> recuperarTodos();
    
}
