
package edu.student.itson.dissof.dto.datos;

import java.util.List;


public class SucursalIdsRelacionesDTO extends SucursalDTO{

    private List<Long> idsProductoInventario;
    
    private Long idDireccion;
    
    public SucursalIdsRelacionesDTO(
            Long id,
            Boolean esMatriz,
            Long idDireccion) {
        
        super(
              id,
              esMatriz);
        
        this.idDireccion = idDireccion;
    }
    
    public SucursalIdsRelacionesDTO(
            Boolean esMatriz,
            Long idDireccion) {
        
        super(
              esMatriz);
        
        this.idDireccion = idDireccion;
    }

    @Override
    public Long getIdDireccion() {
        return idDireccion;
    }

    @Override
    public List<Long> getIdsProductosInventario() {
        return idsProductoInventario;
    }
}
