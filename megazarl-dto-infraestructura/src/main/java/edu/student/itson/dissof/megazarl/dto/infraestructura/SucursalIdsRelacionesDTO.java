
package edu.student.itson.dissof.megazarl.dto.infraestructura;

import java.util.List;


public class SucursalIdsRelacionesDTO extends SucursalDTO{

    private List<IdProductoInventarioDTO> idsProductoInventario;
    
    private IdDireccionDTO idDireccion;
    
    public SucursalIdsRelacionesDTO(
            Long id,
            Boolean esMatriz,
            IdDireccionDTO idDireccion) {
        
        super(
              id,
              esMatriz);
        
        this.idDireccion = idDireccion;
    }
    
    public SucursalIdsRelacionesDTO(
            Boolean esMatriz,
            IdDireccionDTO idDireccion) {
        
        super(
              esMatriz);
        
        this.idDireccion = idDireccion;
    }

    @Override
    public IdDireccionDTO getIdDireccion() {
        return idDireccion;
    }

    @Override
    public List<IdProductoInventarioDTO> getIdsProductosInventario() {
        return idsProductoInventario;
    }
}
