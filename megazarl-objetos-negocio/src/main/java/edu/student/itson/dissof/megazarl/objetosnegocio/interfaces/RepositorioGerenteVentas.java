package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTONegocios;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
/**
 * RepositorioGerenteVentas.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public interface RepositorioGerenteVentas{
    

    public abstract GerenteVentasDTONegocios recuperarPorId(IdGerenteVentasDTONegocios idGerenteVentasDTO);


    public abstract boolean existePorId(IdGerenteVentasDTONegocios idGerenteVentasDTO);
    
    public abstract void agregar(GerenteVentasDTONegocios gerente);
    
    public abstract void agregar(Collection<GerenteVentasDTONegocios> gerentes);
    
    public abstract List<GerenteVentasDTONegocios> recuperarTodos();
     
}