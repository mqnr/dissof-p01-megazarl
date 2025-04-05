package edu.student.itson.dissof.megazarl.dto;

public class InformacionDerivadaCPDireccionEnvioDTO {
    private String colonia;
    private String ciudad;
    private String estado;

    public InformacionDerivadaCPDireccionEnvioDTO(String colonia, String ciudad, String estado) {
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public String getColonia() {
        return colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getEstado() {
        return estado;
    }

    
}