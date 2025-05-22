
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
import java.util.List;


public class SucursalIdsRelacionesDTO extends SucursalDTO{

    private List<IdEntidadGenerico> idsProductoInventario;
    
    private IdEntidadGenerico idDireccion;
    
    public SucursalIdsRelacionesDTO(
            IdEntidadGenerico id,
            Boolean esMatriz,
            IdEntidadGenerico idDireccion) {
        
        super(
              id,
              esMatriz);
        
        this.idDireccion = idDireccion;
    }
    
    public SucursalIdsRelacionesDTO(
            Boolean esMatriz,
            IdEntidadGenerico idDireccion) {
        
        super(
              esMatriz);
        
        this.idDireccion = idDireccion;
    }

    @Override
    public IdEntidadGenerico getIdDireccion() {
        return idDireccion;
    }

    @Override
    public List<IdEntidadGenerico> getIdsProductosInventario() {
        return idsProductoInventario;
    }
}
