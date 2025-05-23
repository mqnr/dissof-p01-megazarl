
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.AuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdAuxiliarVentasDTONegocios;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

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
    
    public abstract AuxiliarVentasDTONegocios recuperarPorId(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTO);
    
    public abstract boolean existePorId(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTO);
    
    public abstract void agregar(AuxiliarVentasDTONegocios auxiliarVentas);
    
    public abstract void agregar(Collection<AuxiliarVentasDTONegocios> auxiliaresVentas);
     
    public abstract List<AuxiliarVentasDTONegocios> recuperarTodos();
    
}
