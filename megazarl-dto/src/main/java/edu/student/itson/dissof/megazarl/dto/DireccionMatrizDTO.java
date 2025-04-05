
package edu.student.itson.dissof.megazarl.dto;


public class DireccionMatrizDTO {
    private String codigoPostalMatriz;
    private String calleMatriz;
    private String numeroMatriz;

    public DireccionMatrizDTO(String codigoPostalMatriz, String calleMatriz, String numeroMatriz) {
        this.codigoPostalMatriz = codigoPostalMatriz;
        this.calleMatriz = calleMatriz;
        this.numeroMatriz = numeroMatriz;
    }

    public String getCodigoPostalMatriz() {
        return codigoPostalMatriz;
    }

    public String getCalleMatriz() {
        return calleMatriz;
    }

    public String getNumeroMatriz() {
        return numeroMatriz;
    }
    
    
}
