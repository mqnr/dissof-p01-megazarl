
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;


public class Paqueteria {
    
    private ObjectId id;
    
    private Long idLong;
    
    private String nombre;
    
    private Float cobroKg;
    
    private Float cobroHora;
    
    private String direccionImagen;
    
    private Direccion direccion;

    public Paqueteria() {
    }
    
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

    public Long getIdLong() {
        return idLong;
    }

    public void setIdLong(Long idLong) {
        this.idLong = idLong;
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
