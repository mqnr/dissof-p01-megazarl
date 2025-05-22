package edu.student.itson.dissof.megazarl.dto.negocios;

/**
 * PaqueteriaDatosCompletosRelacionesDTO.java

 Clase DTO que representa una paquetería o servicio de envío disponible en el sistema,
 con sus tarifas y datos de identificación para el envío de pedidos.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Manuel Romo López
 * ID: 00000253080
 *
 */
public class PaqueteriaDatosCompletosRelacionesDTO extends PaqueteriaDTO{
    
   
    /**
     * Objeto IdDireccionDTO que representa el ID de la dirección de la paquetería.
     */
    private DireccionDTO direccion;

    public PaqueteriaDatosCompletosRelacionesDTO(
            Long id, 
            String nombre,
            Float cobroKg, 
            Float cobroHora,
            String direccionImagenPaqueteria, 
            DireccionDTO direccion) {
        
        super(
            id,
            nombre, 
            cobroKg,
            cobroHora, 
            direccionImagenPaqueteria);
        
        this.direccion = direccion;
    }
    
    public PaqueteriaDatosCompletosRelacionesDTO(
            String nombre,
            Float cobroKg, 
            Float cobroHora,
            String direccionImagenPaqueteria, 
            DireccionDTO direccion) {
        
        super(
            nombre, 
            cobroKg,
            cobroHora, 
            direccionImagenPaqueteria);
        
        this.direccion = direccion;
    }

    public DireccionDTO getDireccion() {
        return direccion;
    }

    @Override
    public Long getIdDireccion() {
        return direccion.getId();
    }
    
    
}