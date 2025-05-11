
package edu.student.itson.dissof.megazarl.dto.infraestructura;

import edu.student.itson.dissof.megazarl.dto.infraestructura.DireccionDTO;



/**
 * Objeto DTO que contiene la información a actualizar de un cliente registrado.
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
public class ActualizacionClienteDTO {
    
    /**
     * Objeto Long que representa el ID del cliente a actualizar.
     */
    private Long id;
    
    /**
     * Objeto String que representa los nuevos nombres del cliente.
     */
    private String nombres;
    
    /**
     * Objeto String que representa el nuevo apellido paterno del cliente.
     */
    private String apellidoPaterno;
    
    /**
     * Objeto String que representa el nuevo apellido materno del cliente.
     */
    private String apellidoMaterno;
    
    /**
     * Objeto DireccionDTO que representa la neuva dirección de envío del cliente.
     */
    private DireccionDTO direccionEnvio;

    /**
     * Contructor de la clase que recibe el ID del cliente actualizar.
     * @param id Objeto Long que representa el ID de cliente a actualizar.
     */
    public ActualizacionClienteDTO(Long id) {
        this.id = id;
    }

    /**
     * Método que permite obtener el ID del cliente a actualizar.
     * @return Objeto Long que representa el ID del cliente a actualizar.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que permite obtener los nuevos nombres del cliente.
     * @return Objeto String que representa los nuevos nombres del cliente.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Método que permite obtener el nuevo apellido paterno del cliente.
     * @return Objeto String que representa el nuevo apellido paterno del cliente.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Método que permite obtener el nuevo apellido materno del cliente.
     * @return Objeto String que representa el nuevo apellido materno del cliente.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Método que permite obtener la nueva dirección de envío del cliente.
     * @return Objeto String que representa la nueva dirección de envío del cliente.
     */
    public DireccionDTO getDireccionEnvio() {
        return direccionEnvio;
    }

    /**
     * Método que permite establecer un nuevo valor para el atributo nombres del cliente.
     * @param nombres Objeto String que representa los nuevos nombres del cliente.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Método que permite establecer un nuevo valor para el atributo apellido paterno del cliente
     * @param apellidoPaterno Objeto String que representa el nuevo apellido paterno del cliente.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Método que permite establecer un nuevo valor para el atributo apellido materno del cliente
     * @param apellidoMaterno Objeto String que representa el nuevo apellido materno del cliente.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Método que permite establecer un nuevo valor para el atributo direccion del cliente
     * @param direcionEnvio Objeto DireccionDTO que representa la nueva direccion del cliente.
     */
    public void setDireccionEnvio(DireccionDTO direcionEnvio) {
        this.direccionEnvio = direcionEnvio;
    }
    
    /**
     * Método que permite determinar si este objeto DTO tiene nombres de cliente.
     * @return Valor true, si el valor del atributo nombres no es nulo, false en caso
     * contrario.
     */
    public boolean tieneNombres(){
        return nombres != null;
    }
    
    /**
     * Método que permite determinar si este objeto DTO tiene apellido paterno de cliente.
     * @return Valor true, si el valor del atributo apellidoPaterno no es nulo, false en caso
     * contrario.
     */
    public boolean tieneApellidoPaterno(){
        return nombres != null;
    }
    
    /**
     * Método que permite determinar si este objeto DTO tiene apellido materno de cliente.
     * @return Valor true, si el valor del atributo apellidoMaterno no es nulo, false en caso
     * contrario.
     */
    public boolean tieneApellidoMaterno(){
        return nombres != null;
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
