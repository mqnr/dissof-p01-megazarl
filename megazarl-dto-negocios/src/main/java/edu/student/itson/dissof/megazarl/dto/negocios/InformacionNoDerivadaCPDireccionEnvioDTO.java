package edu.student.itson.dissof.megazarl.dto.negocios;

/**
 * InformacionNoDerivadaCPDireccionEnvioDTO.java
 *
 * Clase que representa un objeto de transferencia de datos que contiene
 * la información no derivada de una dirección de envío, que es ingresada
 * directamente por el usuario, incluyendo el ID del cliente, número, calle
 * y código postal.
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
public class InformacionNoDerivadaCPDireccionEnvioDTO {
    private Long idCliente;
    private String numero;
    private String calle;
    private String colonia;
    private String codigoPostal;

    public InformacionNoDerivadaCPDireccionEnvioDTO(
            Long idCliente,
            String numero, 
            String calle,
            String colonia,
            String codigoPostal) {
        
        this.idCliente = idCliente;
        this.numero = numero;
        this.calle = calle;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
    }

    public InformacionNoDerivadaCPDireccionEnvioDTO(
            String numero,
            String calle,
            String colonia, 
            String codigoPostal) {
        
        this.numero = numero;
        this.calle = calle;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
    
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
