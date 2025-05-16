
package edu.student.itson.dissof.megazarl.dto.infraestructura;


public class SucursalDatosCompletosRelacionesDTO extends SucursalDTO{

    /**
     * Objeto DireccionDTO que representa la dirección de la sucursal.
     */
    private DireccionDTO direccion;
    
    public SucursalDatosCompletosRelacionesDTO(
            Long id,
            Boolean esMatriz,
            DireccionDTO direccion) {
        
        super(
              id,
              esMatriz);
        
        this.direccion = direccion;
    }
    
    public SucursalDatosCompletosRelacionesDTO(
            Boolean esMatriz,
            DireccionDTO direccion) {
        
        super(
              esMatriz);
        
        this.direccion = direccion;
    }

    @Override
    public IdDireccionDTO getIdDireccion() {
        return new IdDireccionDTO(direccion.getId());
    }
    
}
