
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;


/**
 * Clase que representa una paquetería registrada en el sistema que realiza el
 * envío y traslado de productos entre sucursales y hacie el cliente.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class Paqueteria {
    
    private ObjectId id;
    
    private String nombre;
    
    private Float cobroKg;
    
    private Float cobroHora;
    
    private String direccionImagen;
    
    private Direccion direccion;

    public Paqueteria() {}
    
    public Paqueteria(String nombre, Float cobroKg, Float cobroHora, String direccionImagen, Direccion direccion) {
        this.nombre = nombre;
        this.cobroKg = cobroKg;
        this.cobroHora = cobroHora;
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

    public Float getCobroKg() {
        return cobroKg;
    }

    public void setCobroKg(Float cobroKg) {
        this.cobroKg = cobroKg;
    }

    public Float getCobroHora() {
        return cobroHora;
    }

    public void setCobroHora(Float cobroHora) {
        this.cobroHora = cobroHora;
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
    
    
    
}
