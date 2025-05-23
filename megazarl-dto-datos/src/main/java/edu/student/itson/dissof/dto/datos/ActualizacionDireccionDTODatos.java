
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;

/**
 * IdProductoDTO.java
 *
 * Clase DTO que que representa lso datos a actualizar de una dirección
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
public class ActualizacionDireccionDTODatos {
    
    /**
     * Objeto Long que representa el ID de la dirección.
     */
    private IdEntidadGenericoDatos id;
    
    /**
     * Objeto String que representa el estado de la dirección.
     */
    private String estado;
    
    /**
     * Objeto String que representa la ciudad de la dirección.
     */
    private String ciudad;
    
    /**
     * Objeto String que representa el Código Postal de la dirección.
     */
    private String codigoPostal;
    
    /**
     * Objeto String que representa la colonia de la dirección.
     */
    private String colonia;
    
    /**
     * Objeto String que representa la calle de la dirección.
     */
    private String calle;
    
    /**
     * Objeto String que representa el número de la dirección.
     */
    private String numero;

    /**
     * Constructor de la clase que recibe los datos necesarios para actualizar una dirección.
     * @param id            Objeto IdEntidadGenericoDatos que representa el ID de la dirección.
     * @param estado        Objeto String que representa el estado de la dirección.
     * @param ciudad        Objeto String que representa la ciudad de la dirección.
     * @param codigoPostal  Objeto String que representa el Código Postal de la dirección.
     * @param colonia       Objeto String que representa la colonia de la dirección.
     * @param calle         Objeto String que representa la calle de la dirección.
     * @param numero        Objeto String que representa el número de la dirección.
     */
    public ActualizacionDireccionDTODatos(IdEntidadGenericoDatos id, String estado, String ciudad, String codigoPostal, String colonia, String calle, String numero) {
        this.id = id;
        this.estado = estado;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }

    /**
     * Método que permite obtener el ID de la dirección.
     * @return Objeto Long que representa el ID de la dirección.
     */
    public IdEntidadGenericoDatos getId() {
        return id;
    }

    /**
     * Método que permite obener el estado de la dirección.
     * @return Objeto String que representa el estado de la dirección.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Método que permite obtener la ciudad de la dirección.
     * @return Objeto String que representa la ciudad de la dirección.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Método que permite obtener el Código Postal de la dirección.
     * @return Objeto String que representa el Código Postal de la dirección.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Método que permite obtener la colonia de la dirección.
     * @return Objeto String que representa la colonia de la dirección.
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Método que permite obtener la calle de la dirección.
     * @return Objeto String que representa la calle de la dirección.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Método que permite obtener el número de la dirección.
     * @return Objeto String que representa el número de la dirección.
     */
    public String getNumero() {
        return numero;
    }
    
    /**
     * Método que permite determinar si este objeto DTO tiene estado de dirección.
     * @return Valor true, si el valor del atributo estado no es nulo, false en caso
     * contrario.
     */
    public boolean tieneEstado(){
        return this.estado != null;
    }
    
    /**
     * Método que permite determinar si este objeto DTO tiene ciudad de dirección.
     * @return Valor true, si el valor del atributo ciudad no es nulo, false en caso
     * contrario.
     */
    public boolean tieneCiudad(){
        return this.ciudad != null;
    }
    
    /**
     * Método que permite determinar si este objeto DTO tiene Código Postal de dirección.
     * @return Valor true, si el valor del atributo codigoPostal no es nulo, false en caso
     * contrario.
     */
    public boolean tieneCodigoPostal(){
        return this.codigoPostal != null;
    }
    
    /**
     * Método que permite determinar si este objeto DTO tiene Colonia de dirección.
     * @return Valor true, si el valor del atributo colonia no es nulo, false en caso
     * contrario.
     */
    public boolean tieneColonia(){
        return this.colonia != null;
    }
    
    /**
     * Método que permite determinar si este objeto DTO tiene calle de dirección.
     * @return Valor true, si el valor del atributo calle no es nulo, false en caso
     * contrario.
     */
    public boolean tieneCalle(){
        return this.calle != null;
    }
    
    /**
     * Método que permite determinar si este objeto DTO tiene número de dirección.
     * @return Valor true, si el valor del atributo numero no es nulo, false en caso
     * contrario.
     */
    public boolean tieneNumero(){
        return this.numero != null;
    }

}
