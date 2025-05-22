
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;


public class Proveedor {
    
    private ObjectId id;

    private String nombre;
    
    private String telefono;
    
    private String correoElectronico;
    
    private String direccionImagen;
    
    private ObjectId idDireccion;

    public Proveedor() {
    }

    public Proveedor(String nombre, String telefono, String direccionImagen, ObjectId direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccionImagen = direccionImagen;
        this.idDireccion = direccion;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    public ObjectId getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(ObjectId idDireccion) {
        this.idDireccion = idDireccion;
    }
    
    
}
