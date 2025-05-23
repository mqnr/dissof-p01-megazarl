package edu.student.itson.dissof.megazarl.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
/**
 * RepositorioGerenteVentas.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public interface RepositorioGerenteVentas{
    
    /**
     * 
     * @param idGerenteVentasDTONegocios
     * @return 
     */
    public abstract GerenteVentasDTONegocios recuperarPorId(IdGerenteVentasDTONegocios idGerenteVentasDTONegocios);

    /**
     * 
     * @param idGerenteVentasDTONegocios
     * @return 
     */
    public abstract boolean existePorId(IdGerenteVentasDTONegocios idGerenteVentasDTONegocios);
    
}