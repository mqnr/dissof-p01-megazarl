
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;


public class AuxiliarVentasDTODatos {
    
    /**
     * Objeto Long que representa el ID del Auxiliar de ventas.
     */
    private IdEntidadGenericoDatos id;
    
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
    
    private IdEntidadGenericoDatos idSucursal;

    /**
     * Constructor de la clase que recibe valores para todos los atributos.
     * @param id                Objeto IdEntidadGenericoDatos que representa el ID del Auxiliar de ventas.
     * @param nombres           Objeto String que representa el o lo nombres del Auxiliar de ventas.
     * @param apellidoPaterno   Objeto String que representa el apellido paterno del Auxiliar de ventas.
     * @param apellidoMaterno   Objeto String que representa el apellido materno del Auxiliar de ventas.
     * @param idSucursal
     */
    public AuxiliarVentasDTODatos(
            IdEntidadGenericoDatos id,
            String nombres,
            String apellidoPaterno, 
            String apellidoMaterno,
            IdEntidadGenericoDatos idSucursal) {
        
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idSucursal = idSucursal;
    }
    
    /**
     * Constructor de la clase que recibe valores para todos los atributos, excepto el ID.
     * @param nombres           Objeto String que representa el o lo nombres del Auxiliar de ventas.
     * @param apellidoPaterno   Objeto String que representa el apellido paterno del Auxiliar de ventas.
     * @param apellidoMaterno   Objeto String que representa el apellido materno del Auxiliar de ventas.
     */
    public AuxiliarVentasDTODatos(
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Método que permite obtener el ID del Auxiliar de ventas.
     * @return Objeto IdEntidadGenericoDatos que representa el ID del Auxiliar de ventas.
     */
    public IdEntidadGenericoDatos getId() {
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
     * @param id Objeto IdEntidadGenericoDatos que representa el nuevo ID del Auxiliar de ventas.
     */
    public void setId(IdEntidadGenericoDatos id) {
        this.id = id;
    }

    public IdEntidadGenericoDatos getIdSucursal() {
        return idSucursal;
    }
    
    
    
}
