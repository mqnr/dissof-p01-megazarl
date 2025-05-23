
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.List;


public class SucursalIdsRelacionesDTONegocios extends SucursalDTONegocios{

    private List<IdEntidadGenericoNegocios> idsProductoInventario;
    
    private IdEntidadGenericoNegocios idDireccion;
    
    public SucursalIdsRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            Boolean esMatriz,
            IdEntidadGenericoNegocios idDireccion) {
        
        super(
              id,
              esMatriz);
        
        this.idDireccion = idDireccion;
    }
    
    public SucursalIdsRelacionesDTONegocios(
            Boolean esMatriz,
            IdEntidadGenericoNegocios idDireccion) {
        
        super(
              esMatriz);
        
        this.idDireccion = idDireccion;
    }

    @Override
    public IdEntidadGenericoNegocios getIdDireccion() {
        return idDireccion;
    }

    @Override
    public List<IdEntidadGenericoNegocios> getIdsProductosInventario() {
        return idsProductoInventario;
    }
}
