
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;


public class GerenteVentasDTONegocios {
    
    private IdEntidadGenericoNegocios id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public GerenteVentasDTONegocios(
            IdEntidadGenericoNegocios id,
            String nombre, 
            String apellidoPaterno, 
            String apellidoMaterno) {
        
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }
    
    public GerenteVentasDTONegocios(
            String nombre, 
            String apellidoPaterno, 
            String apellidoMaterno) {
        this.nombres = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }
    public IdEntidadGenericoNegocios getId() {
        return id;
    }

    public void setId(IdEntidadGenericoNegocios id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombre) {
        this.nombres = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
    
    
}
