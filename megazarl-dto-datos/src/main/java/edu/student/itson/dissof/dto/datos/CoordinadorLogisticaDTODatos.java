
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;


public class CoordinadorLogisticaDTODatos {
    
    private IdEntidadGenericoDatos id;
    
    private String nombres;
    
    private String apellidoPaterno;
    
    private String apellidoMaterno;

    public CoordinadorLogisticaDTODatos(IdEntidadGenericoDatos id, String nombres, String apellidoPaterno, String apellidoMaterno) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public CoordinadorLogisticaDTODatos(String nombres, String apellidoPaterno, String apellidoMaterno) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public IdEntidadGenericoDatos getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setId(IdEntidadGenericoDatos id) {
        this.id = id;
    }
    
    
    
}
