package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
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