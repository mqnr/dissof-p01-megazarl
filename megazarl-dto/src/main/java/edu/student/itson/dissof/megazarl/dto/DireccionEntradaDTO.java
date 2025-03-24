
package edu.student.itson.dissof.megazarl.dto;

public class DireccionEntradaDTO {
    private String numeroExterior;
    private String calle;
    private String codigoPostal;

    public DireccionEntradaDTO(String numeroExterior, String calle, String codigoPostal) {
        this.numeroExterior = numeroExterior;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public String getCalle() {
        return calle;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }
    
    
            
            
}
