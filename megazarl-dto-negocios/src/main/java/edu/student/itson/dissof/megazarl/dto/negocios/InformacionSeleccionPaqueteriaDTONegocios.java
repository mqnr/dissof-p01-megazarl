package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * InformacionSeleccionPaqueteriaDTONegocios.java

 Clase DTO que contiene la información básica de una paquetería para ser 
 mostrada durante el proceso de selección de servicio de envío.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
public class InformacionSeleccionPaqueteriaDTONegocios {
    /**
     * Objeto IdEntidadGenericoNegocios que representa el ID de la paquetería.
     */
    private IdEntidadGenericoNegocios idPaqueteria;
    
    /**
     * Objeto String que representa la dirección de la imagen de la paquetería.
     */
    private String direccionImagenPaqueteria;

    /**
     * Consutructor de la clase que recibe la información necesaria para mostrar
     * la paquetería en la selección de servicio de envío.
     * @param idPaqueteria              Objeto IdEntidadGenericoNegocios que representa el ID de la paquetería.
     * @param direccionImagenPaqueteria Objeto String que representa la dirección de la imagen de la paquetería.
     */
    public InformacionSeleccionPaqueteriaDTONegocios(
            
            IdEntidadGenericoNegocios idPaqueteria, 
            String direccionImagenPaqueteria) {
        
        this.idPaqueteria = idPaqueteria;
        this.direccionImagenPaqueteria = direccionImagenPaqueteria;
    }

    /**
     * Método que permite obener el ID de la paquetería a mostrar.
     * @return Objeto Long que representa el ID de la paquetería.
     */
    public IdEntidadGenericoNegocios getIdPaqueteria() {
        return idPaqueteria;
    }

    /**
     * Método que permite obtener la dirección de la imagen de la paquetería.
     * @return Objeto String que representa la dirección de la imagen de la paquetería.
     */
    public String getDireccionImagenPaqueteria() {
        return direccionImagenPaqueteria;
    }
}
