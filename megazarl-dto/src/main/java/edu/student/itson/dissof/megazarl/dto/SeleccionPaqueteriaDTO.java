
package edu.student.itson.dissof.megazarl.dto;


public class SeleccionPaqueteriaDTO {
    private Integer idPaqueteria;
    private String direccionImagenPaqueteria;

    public SeleccionPaqueteriaDTO(Integer idPaqueteria, String direccionImagenPaqueteria) {
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
