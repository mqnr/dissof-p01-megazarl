
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;


public class AuxiliarVentasDTONegocios {
    
    /**
     * Objeto Long que representa el ID del Auxiliar de ventas.
     */
    private IdEntidadGenericoNegocios id;
    
    /**
     * Objeto String que representa el o lo nombres del Auxiliar de ventas.
     */
    private String nombres;
    
    /**
     * Objeto String que representa el apellido paterno del Auxiliar de ventas.
     */
    private String apellidoPaterno;
    
    /**
     * Objeto String que representa el apellido materno del Auxiliar de ventas.
     */
    private String apellidoMaterno;

    /**
     * Constructor de la clase que recibe valores para todos los atributos.
     * @param id                Objeto IdEntidadGenericoNegocios que representa el ID del Auxiliar de ventas.
     * @param nombres           Objeto String que representa el o lo nombres del Auxiliar de ventas.
     * @param apellidoPaterno   Objeto String que representa el apellido paterno del Auxiliar de ventas.
     * @param apellidoMaterno   Objeto String que representa el apellido materno del Auxiliar de ventas.
     */
    public AuxiliarVentasDTONegocios(
            IdEntidadGenericoNegocios id,
            String nombres,
            String apellidoPaterno, 
            String apellidoMaterno) {
        
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }
    
    /**
     * Constructor de la clase que recibe valores para todos los atributos, excepto el ID.
     * @param nombres           Objeto String que representa el o lo nombres del Auxiliar de ventas.
     * @param apellidoPaterno   Objeto String que representa el apellido paterno del Auxiliar de ventas.
     * @param apellidoMaterno   Objeto String que representa el apellido materno del Auxiliar de ventas.
     */
    public AuxiliarVentasDTONegocios(
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Método que permite obtener el ID del Auxiliar de ventas.
     * @return Objeto IdEntidadGenericoNegocios que representa el ID del Auxiliar de ventas.
     */
    public IdEntidadGenericoNegocios getId() {
        return id;
    }

    /**
     * Método que permite recuperar los nombres del Auxiliar de ventas.
     * @return Objeto String que representa el o lo nombres del Auxiliar de ventas.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Método que permite recuperar el apellido paterno del Auxiliar de ventas.
     * @return Objeto String que representa el apellido paterno del Auxiliar de ventas.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Método que permite recuperar el apellido materno del Auxiliar de ventas.
     * @return Objeto String que representa el apellido materno del Auxiliar de ventas.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Método que permite establecer el ID del Auxiliar de ventas.
     * @param id Objeto IdEntidadGenericoNegocios que representa el nuevo ID del Auxiliar de ventas.
     */
    public void setId(IdEntidadGenericoNegocios id) {
        this.id = id;
    }
    
    
    
    
}
