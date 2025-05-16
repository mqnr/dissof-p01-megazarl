
package edu.student.itson.dissof.megazarl.dto.infraestructura;


public class SucursalIdsRelacionesDTO extends SucursalDTO{


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
}
