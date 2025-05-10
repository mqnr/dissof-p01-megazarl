
package edu.student.itson.dissof.megazarl.dto.negocios;


/**
 * InformacionDireccionEnvioActualizadaClienteDTO.java
 
 Clase DTO que contiene la información de una dirección de envío actualizada 
 para un cliente.
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
public class InformacionDireccionEnvioActualizadaClienteDTO {
    
    /**
     * Objeto Long que representa el ID del cliente al que se actualizará la dirección.
     */
    private Long idCliente;
    
    /**
     * Objeto String que representa el nuevo Código Postal.
     */
    private String codigoPostal;
    
    /**
     * Objeto String que representa la nueva colonia.
     */
    private String colonia;
    
    /**
     * Objeto String que representa la nueva calle.
     */
    private String calle;
    
    /**
     * Objeto String que representa el nuevo número.
     */
    private String numero;

    /**
     * Constructor de la clase que recibe la información necesaria para actualizar
     * la dirección de un cliente.
     * @param idCliente     Objeto Long que representa el ID del cliente al que se actualizará la dirección.
     * @param codigoPostal  Objeto String que representa el nuevo Código Postal.
     * @param colonia       Objeto String que representa la nueva colonia.
     * @param calle         Objeto String que representa la nueva calle.
     * @param numero        Objeto String que representa el nuevo número.
     */
    public InformacionDireccionEnvioActualizadaClienteDTO(Long idCliente, String codigoPostal, String colonia, String calle, String numero) {
        this.idCliente = idCliente;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }

    /**
     * Método que permite obtener el ID del cliente.
     * @return Objeto Long que representa el ID del cliente al que se actualizará la dirección.
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * Método que permite obtener el Código Postal.
     * @return Objeto String que representa el nuevo Código Postal.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Método que permite obtener la colonia.
     * @return  Objeto String que representa la nueva colonia.
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Método que permite obtener la calle.
     * @return Objeto String que representa la nueva calle.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Método que permite otener el número
     * @return Objeto String que representa el nuevo número.
     */
    public String getNumero() {
        return numero;
    }
    
    
    
}
