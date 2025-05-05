
package edu.student.itson.dissof.megazarl.dto.infraestructura;


public class DatosTiempoTrasladoUbicacionesDTO {
    
    private String numeroA;
    private String calleA;
    private String coloniaA;
    private String codigoPostalA;
    
    private String numeroB;
    private String calleB;
    private String coloniaB;
    private String codigoPostalB;

    public DatosTiempoTrasladoUbicacionesDTO(String numeroA, String calleA, String coloniaA, String codigoPostalA, String numeroB, String calleB, String coloniaB, String codigoPostalB) {
        this.numeroA = numeroA;
        this.calleA = calleA;
        this.coloniaA = coloniaA;
        this.codigoPostalA = codigoPostalA;
        this.numeroB = numeroB;
        this.calleB = calleB;
        this.coloniaB = coloniaB;
        this.codigoPostalB = codigoPostalB;
    }

    public String getNumeroA() {
        return numeroA;
    }

    public String getCalleA() {
        return calleA;
    }

    public String getColoniaA() {
        return coloniaA;
    }

    public String getCodigoPostalA() {
        return codigoPostalA;
    }

    public String getNumeroB() {
        return numeroB;
    }

    public String getCalleB() {
        return calleB;
    }

    public String getColoniaB() {
        return coloniaB;
    }

    public String getCodigoPostalB() {
        return codigoPostalB;
    }

}
