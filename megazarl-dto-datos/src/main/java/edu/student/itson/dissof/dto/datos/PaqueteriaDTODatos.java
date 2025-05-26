package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;

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
public class PaqueteriaDTODatos{

    /**
    * Objeto IdEntidadGenericoDatos que representa el ID de la paquetería.
    */
    protected IdEntidadGenericoDatos id;

    /**
     * Objeto String que representa el nombre de la paquetería.
     */
    protected String nombre;

    /**
     * Objeto Float que representa el costo de envío por kilogramo.
     */
    protected Float cobroKg;

    /**
     * Objeto Float que representa el costo de envío por hora.
     */
    protected Float cobroHora;
    
    /**
     * Objeto String que representa la dirección de la imagen de la paquetería.
     */
    protected String direccionImagen;
    
    /**
     * Objeto DireccionDTODatos que representa la dirección de la paquetería.
     */
    private DireccionDTODatos direccion;

    public PaqueteriaDTODatos(
            IdEntidadGenericoDatos id,
            String nombre,
            Float cobroKg, 
            Float cobroHora, 
            String direccionImagen,
            DireccionDTODatos direccion) {
        
        this.id = id;
        this.nombre = nombre;
        this.cobroKg = cobroKg;
        this.cobroHora = cobroHora;
        this.direccionImagen = direccionImagen;
        this.direccion = direccion;
    }
    
    public PaqueteriaDTODatos(
            String nombre,
            Float cobroKg, 
            Float cobroHora, 
            String direccionImagen,
            DireccionDTODatos direccion) {
        
        this.nombre = nombre;
        this.cobroKg = cobroKg;
        this.cobroHora = cobroHora;
        this.direccionImagen = direccionImagen;
        this.direccion = direccion;
    }
    

    public IdEntidadGenericoDatos getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Float getCobroKg() {
        return cobroKg;
    }

    public Float getCobroHora() {
        return cobroHora;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public DireccionDTODatos getDireccion() {
        return direccion;
    }
}