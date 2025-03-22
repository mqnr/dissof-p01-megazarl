package edu.student.itson.dissof.megazarl.dto;

public class InformacionSeleccionPaqueteriaDTO {

    private Integer idPaqueteria;
    private String direccionImagenPaqueteria;

    public InformacionSeleccionPaqueteriaDTO(Integer idPaqueteria, String direccionImagenPaqueteria) {
        this.idPaqueteria = idPaqueteria;
        this.direccionImagenPaqueteria = direccionImagenPaqueteria;
    }

    public Integer getIdPaqueteria() {
        return idPaqueteria;
    }

    public String getDireccionImagenPaqueteria() {
        return direccionImagenPaqueteria;
    }
}
