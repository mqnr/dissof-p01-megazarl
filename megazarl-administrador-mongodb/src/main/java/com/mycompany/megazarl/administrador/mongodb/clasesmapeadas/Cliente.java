
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;

public class Cliente {
    
    private ObjectId id;
    
    private String nombres;
    
    private String apellidoMaterno;
    
    private String apellidoPaterno;
    
    private String telefono;
    
    private String correoElectronico;
    
    private ObjectId idDireccionEnvio;

    public Cliente() {
        
    }
     
    public Cliente(String nombres, String apellidoMaterno, String apellidoPaterno, String telefono, String correoElectronico, ObjectId idDireccionEnvio) {
        this.nombres = nombres;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.idDireccionEnvio = idDireccionEnvio;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public ObjectId getIdDireccionEnvio() {
        return idDireccionEnvio;
    }

    public void setIdDireccionEnvio(ObjectId idDireccionEnvio) {
        this.idDireccionEnvio = idDireccionEnvio;
    }
   
}
