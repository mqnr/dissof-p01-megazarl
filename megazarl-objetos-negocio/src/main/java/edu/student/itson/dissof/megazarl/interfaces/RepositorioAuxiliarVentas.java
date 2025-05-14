
package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.infraestructura.AuxiliarVentasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdAuxiliarVentasDTO;

/**
 * RepositorioAuxiliarVentas.java
 * 
 * Interfaz que debe implementar cualquier repositorio de un objeto de negocio.
 * 
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public interface RepositorioAuxiliarVentas extends RepositorioInmutable<AuxiliarVentasDTO>{
    
    public abstract AuxiliarVentasDTO recuperarPorId(IdAuxiliarVentasDTO idAuxiliarVentasDTO);
    
    public abstract boolean existePorId(IdAuxiliarVentasDTO idAuxiliarVentasDTO);
    
}
