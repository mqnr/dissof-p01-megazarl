package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

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
public class PaqueteriaIdsRelacionesDTONegocios extends PaqueteriaDTONegocios{

    
    private IdEntidadGenericoNegocios idDireccionDTO;

    public PaqueteriaIdsRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            String nombre,
            Float cobroKg, 
            Float cobroHora, 
            String direccionImagenPaqueteria,
            IdEntidadGenericoNegocios idDireccionDTO) {
        
        super(
            id,
            nombre,
            cobroKg, 
            cobroHora,
            direccionImagenPaqueteria);
        
        this.idDireccionDTO = idDireccionDTO;
    }

    @Override
    public IdEntidadGenericoNegocios getIdDireccion() {
        return idDireccionDTO;
    }
}