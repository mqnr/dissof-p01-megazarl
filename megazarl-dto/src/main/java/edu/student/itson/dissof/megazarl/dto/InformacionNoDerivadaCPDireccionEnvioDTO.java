
package edu.student.itson.dissof.megazarl.dto;

public class InformacionNoDerivadaCPDireccionEnvioDTO {
    private Integer idCliente;
    private String numero;
    private String calle;
    private String codigoPostal;

    public InformacionNoDerivadaCPDireccionEnvioDTO(Integer idCliente, String numero, String calle, String codigoPostal) {
        this.idCliente = idCliente;
        this.numero = numero;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
    }

    public InformacionNoDerivadaCPDireccionEnvioDTO(String numero, String calle, String codigoPostal) {
        this.numero = numero;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
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

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    

    
}
