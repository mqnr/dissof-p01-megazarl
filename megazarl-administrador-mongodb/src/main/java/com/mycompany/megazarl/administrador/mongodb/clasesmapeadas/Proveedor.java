
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;


public class Proveedor {
    
    private ObjectId id;

    private String nombre;
    
    private String telefono;
    
    private String direccionImagen;
    
    private Direccion direccion;

    public Proveedor() {
    }

    public Proveedor(String nombre, String telefono, String direccionImagen, Direccion direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccionImagen = direccionImagen;
        this.direccion = direccion;
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", direccionImagen=" + direccionImagen + ", direccion=" + direccion + '}';
    }
    
    
    
}
