

package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;


public class ClienteDTODatos {
    
    private IdEntidadGenericoDatos id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correoElectronico;
    private DireccionDTODatos direccionEnvio;

    public ClienteDTODatos(
            IdEntidadGenericoDatos id, 
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno, 
            String telefono, 
            String correoElectronico,
            DireccionDTODatos direccionEnvio) {
        
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionEnvio = direccionEnvio;
    }
    
    public ClienteDTODatos(
            String nombres, 
            String apellidoPaterno,
            String apellidoMaterno,
            String telefono, 
            String correoElectronico,
            DireccionDTODatos direccionEnvio) {
        
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionEnvio = direccionEnvio;
    }

    public ClienteDTODatos(
            String nombres, 
            String apellidoPaterno,
            String telefono, 
            String correoElectronico,
            DireccionDTODatos direccionEnvio) {
        
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionEnvio = direccionEnvio;
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

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public DireccionDTODatos getDireccionEnvio() {
        return direccionEnvio;
    }
    
    

}
