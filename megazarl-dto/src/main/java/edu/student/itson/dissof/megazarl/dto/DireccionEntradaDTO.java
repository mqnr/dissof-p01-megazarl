
package edu.student.itson.dissof.megazarl.dto;

public class DireccionEntradaDTO {
    private Integer idCliente;
    private String numero;
    private String calle;
    private String codigoPostal;

    public DireccionEntradaDTO(Integer idCliente, String numero, String calle, String codigoPostal) {
        this.idCliente = idCliente;
        this.numero = numero;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNumero() {
        return numero;
    }

    public String getCalle() {
        return calle;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    
    
    
            
            
}
