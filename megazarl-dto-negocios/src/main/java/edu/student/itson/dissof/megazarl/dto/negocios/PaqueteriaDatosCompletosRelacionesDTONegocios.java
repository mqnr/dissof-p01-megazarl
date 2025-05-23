package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * PaqueteriaDatosCompletosRelacionesDTONegocios.java

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
public class PaqueteriaDatosCompletosRelacionesDTONegocios extends PaqueteriaDTONegocios{
    
   
    /**
     * Objeto IdDireccionDTO que representa el ID de la dirección de la paquetería.
     */
    private DireccionDTONegocios direccion;

    public PaqueteriaDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id, 
            String nombre,
            Float cobroKg, 
            Float cobroHora,
            String direccionImagenPaqueteria, 
            DireccionDTONegocios direccion) {
        
        super(
            id,
            nombre, 
            cobroKg,
            cobroHora, 
            direccionImagenPaqueteria);
        
        this.direccion = direccion;
    }
    
    public PaqueteriaDatosCompletosRelacionesDTONegocios(
            String nombre,
            Float cobroKg, 
            Float cobroHora,
            String direccionImagenPaqueteria, 
            DireccionDTONegocios direccion) {
        
        super(
            nombre, 
            cobroKg,
            cobroHora, 
            direccionImagenPaqueteria);
        
        this.direccion = direccion;
    }

    public DireccionDTONegocios getDireccion() {
        return direccion;
    }

    @Override
    public IdEntidadGenericoNegocios getIdDireccion() {
        return direccion.getId();
    }
    
    
}