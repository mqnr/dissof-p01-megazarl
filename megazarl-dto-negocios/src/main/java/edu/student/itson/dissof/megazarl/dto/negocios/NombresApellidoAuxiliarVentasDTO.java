
package edu.student.itson.dissof.megazarl.dto.negocios;

/**
 *
 * @author Manuel Romo López
 */
public class NombresApellidoAuxiliarVentasDTO {
    
    private String nombreAuxiliarVentas;
    private String apellidoPaternoAuxiliarVentas;

    public NombresApellidoAuxiliarVentasDTO(String nombreAuxiliarVentas, String apellidoPaternoAuxiliarVentas) {
        this.nombreAuxiliarVentas = nombreAuxiliarVentas;
        this.apellidoPaternoAuxiliarVentas = apellidoPaternoAuxiliarVentas;
    }
    
    public String getNombreAuxiliarVentas() {
        return nombreAuxiliarVentas;
    }

    public String getApellidoPaternoAuxiliarVentas() {
        return apellidoPaternoAuxiliarVentas;
    }
    
}
