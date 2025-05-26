
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;


public class GerenteVentasDTODatos {
    
    private IdEntidadGenericoDatos id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public GerenteVentasDTODatos(
            IdEntidadGenericoDatos id, 
            String nombres,
            String apellidoPaterno, 
            String apellidoMaterno) {
        
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public GerenteVentasDTODatos(
            String nombres,
            String apellidoPaterno, 
            String apellidoMaterno) {

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
    
}
