
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * AuxiliarVentasDTONegocios.java
 * 
 * @author Manuel Romo López
 */
public class AuxiliarVentasDTONegocios {
    
    /**
     * Objeto IdEntidadGenericoNegocios que representa el ID del Auxiliar de ventas.
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
     * Objeto IdEntidadGenericoNegocios que representa el ID de la sucursal a la que está asociado el Auxiliar de Ventas.
     */
    private IdEntidadGenericoNegocios idSucursal;

    /**
     * Constructor de la clase que recibe valores para todos los atributos.
     * @param id                Objeto IdEntidadGenericoNegocios que representa el ID del Auxiliar de ventas.
     * @param nombres           Objeto String que representa el o lo nombres del Auxiliar de ventas.
     * @param apellidoPaterno   Objeto String que representa el apellido paterno del Auxiliar de ventas.
     * @param apellidoMaterno   Objeto String que representa el apellido materno del Auxiliar de ventas.
     * @param idSucursal        Objeto IdEntidadGenericoNegocios que representa el ID de la sucursal a la que está asociado el Auxiliar de Ventas.
     */
    public AuxiliarVentasDTONegocios(
            IdEntidadGenericoNegocios id,
            String nombres,
            String apellidoPaterno, 
            String apellidoMaterno,
            IdEntidadGenericoNegocios idSucursal) {
        
        this.id = id;
        this.idSucursal = idSucursal;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }
    
    /**
     * Constructor de la clase que recibe valores para todos los atributos, excepto el ID.
     * @param nombres           Objeto String que representa el o lo nombres del Auxiliar de ventas.
     * @param apellidoPaterno   Objeto String que representa el apellido paterno del Auxiliar de ventas.
     * @param apellidoMaterno   Objeto String que representa el apellido materno del Auxiliar de ventas.
     * @param idSucursal        Objeto IdEntidadGenericoNegocios que representa el ID de la sucursal a la que está asociado el Auxiliar de Ventas.
     */
    public AuxiliarVentasDTONegocios(
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno,
            IdEntidadGenericoNegocios idSucursal) {
        
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idSucursal = idSucursal;
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
    
    /**
     * Método que permite obtener el ID de la sucursal de este auxiliar de ventas.
     * @return Objeto IdEntidadGenericoNegocios que representa el ID de la sucursal de este auxiliar de ventas.
     */
    public IdEntidadGenericoNegocios getIdSucursal() {
        return idSucursal;
    }

    
}
