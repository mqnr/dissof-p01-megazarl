/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;

/**
 *
 * @author Laboratorios
 */
public class Producto {
    
    
    private ObjectId id;
    
    private Long idLong;
    
    private String nombre;
    
    private String variedad;
    
    private String descripcion;
    
    private Integer milesSemillas;
    
    private Double precio;
    
    private Double pesoKg;
    
    private String direccionImagen;
    
    private Proveedor proveedor;

    public Producto(String nombre, String variedad, Integer milesSemillas, Double precio, Double pesoKg, String direccionImagen, Proveedor proveedor) {
        this.nombre = nombre;
        this.variedad = variedad;
        this.milesSemillas = milesSemillas;
        this.precio = precio;
        this.pesoKg = pesoKg;
        this.direccionImagen = direccionImagen;
        this.proveedor = proveedor;
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

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMilesSemillas() {
        return milesSemillas;
    }

    public void setMilesSemillas(Integer milesSemillas) {
        this.milesSemillas = milesSemillas;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
            
    
    
    
}
