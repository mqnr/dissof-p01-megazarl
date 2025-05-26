
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;


/**
 * Objeto DTO que contiene la información a actualizar de un cliente registrado.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class ActualizacionClienteDTODatos {
    
    /**
     * Objeto Long que representa el ID del cliente a actualizar.
     */
    private IdEntidadGenericoDatos id;
    
    /**
     * Objeto DireccionDTO que representa la neuva dirección de envío del cliente.
     */
    private DireccionDTODatos direccionEnvio;

    /**
     * Contructor de la clase que recibe el ID del cliente actualizar.
     * @param id Objeto Long que representa el ID de cliente a actualizar.
     */
    public ActualizacionClienteDTODatos(IdEntidadGenericoDatos id) {
        this.id = id;
    }

    /**
     * Método que permite obtener el ID del cliente a actualizar.
     * @return Objeto Long que representa el ID del cliente a actualizar.
     */
    public IdEntidadGenericoDatos getId() {
        return id;
    }

    /**
     * Método que permite obtener la nueva dirección de envío del cliente.
     * @return Objeto String que representa la nueva dirección de envío del cliente.
     */
    public DireccionDTODatos getDireccionEnvio() {
        return direccionEnvio;
    }

    /**
     * Método que permite establecer un nuevo valor para el atributo direccion del cliente
     * @param direccionEnvio Objeto DireccionDTODatos que representa la nueva direccion del cliente.
     */
    public void setDireccionEnvio(DireccionDTODatos direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
    
    /**
     * Método que permite determinar si este objeto DTO tiene dirección de envío de cliente.
     * @return Valor true, si el valor del atributo direccion no es nulo, false en caso
     * contrario.
     */
    public boolean tieneDireccionEnvio(){
        return direccionEnvio != null;
    }
    
}
