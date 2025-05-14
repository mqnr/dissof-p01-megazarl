package edu.student.itson.dissof.megazarl.dto.negocios;
/**
 * NombresApellidoGerenteVentasDTO.java
 *
 * Clase que representa un objeto de transferencia de datos que contiene
 * los nombres y apellido materno de un gerente de ventas, utilizados para identificación
 * y presentación en la interfaz de usuario.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 *
 */
public class NombresApellidoGerenteVentasDTO {
    
    private String nombresGerenteVentas;
    private String apellidoPaternoGerenteVentas;

    public NombresApellidoGerenteVentasDTO(String nombresGerenteVentas, String apellidoPaternoGerenteVentas) {
        this.nombresGerenteVentas = nombresGerenteVentas;
        this.apellidoPaternoGerenteVentas = apellidoPaternoGerenteVentas;
    }

    public String getNombresGerenteVentas() {
        return nombresGerenteVentas;
    }

    public void setNombresGerenteVentas(String nombresGerenteVentas) {
        this.nombresGerenteVentas = nombresGerenteVentas;
    }

    public String getApellidoPaternoGerenteVentas() {
        return apellidoPaternoGerenteVentas;
    }

    public void setApellidoPaternoGerenteVentas(String apellidoPaternoGerenteVentas) {
        this.apellidoPaternoGerenteVentas = apellidoPaternoGerenteVentas;
    }
    
}