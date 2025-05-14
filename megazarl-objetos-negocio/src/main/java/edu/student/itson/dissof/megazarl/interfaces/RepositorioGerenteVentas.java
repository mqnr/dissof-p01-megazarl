package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.infraestructura.GerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdGerenteVentasDTO;
/**
 * RepositorioGerenteVentas.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public interface RepositorioGerenteVentas extends RepositorioInmutable<GerenteVentasDTO>{
    
    /**
     * 
     * @param idGerenteVentasDTO
     * @return 
     */
    public abstract GerenteVentasDTO recuperarPorId(IdGerenteVentasDTO idGerenteVentasDTO);

    /**
     * 
     * @param idGerenteVentasDTO
     * @return 
     */
    public abstract boolean existePorId(IdGerenteVentasDTO idGerenteVentasDTO);
    
}