package edu.student.itson.dissof.megazarl.dto;

public class DetallesDerivadosDireccionDTO {
    private String colonia;
    private String ciudad;
    private String estado;

    public DetallesDerivadosDireccionDTO(String colonia, String ciudad, String estado) {
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