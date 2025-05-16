
package edu.student.itson.dissof.megazarl.dto.infraestructura;


import com.google.gson.annotations.SerializedName;

public class DatosTiempoTrasladoUbicacionesDTO {
    @SerializedName("codigo_postal_origen")
    private String codigoPostalA;

    @SerializedName("codigo_postal_destino")
    private String codigoPostalB;

    public DatosTiempoTrasladoUbicacionesDTO(
            String numeroA, 
            String calleA, 
            String coloniaA,
            String codigoPostalA, 
            String numeroB, 
            String calleB,
            String coloniaB, 
            String codigoPostalB) {
        
        this.codigoPostalA = codigoPostalA;
        this.codigoPostalB = codigoPostalB;
    }

    public String getCodigoPostalA() {
        return codigoPostalA;
    }

    public String getCodigoPostalB() {
        return codigoPostalB;
    }

}
