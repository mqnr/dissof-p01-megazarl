package edu.student.itson.dissof.megazarl.dto;

/**
 * InformacionSeleccionPaqueteriaDTO.java
 *
 * Clase que representa un objeto de transferencia de datos que contiene
 * la información básica de una paquetería para ser mostrada durante el
 * proceso de selección de servicio de envío.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
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
