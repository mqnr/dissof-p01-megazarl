
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;

/**
 * Clase que representa un producto en el catálogo registrado en el sistema.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */

public class Producto {
    
    private ObjectId id;

    private String nombre;
    
    private String variedad;
    
    private String descripcion;
    
    private Integer milesSemillas;
    
    private Double precio;
    
    private Double pesoKg;
    
    private String direccionImagen;
    
    private ObjectId idProveedor;

    public Producto(){}
    
    public Producto(
            String nombre, 
            String variedad, 
            String descripcion,
            Integer milesSemillas, 
            Double precio,
            Double pesoKg, 
            String direccionImagen, 
            ObjectId idProveedor) {
        
        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.milesSemillas = milesSemillas;
        this.precio = precio;
        this.pesoKg = pesoKg;
        this.direccionImagen = direccionImagen;
        this.idProveedor = idProveedor;
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

    public ObjectId getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(ObjectId idProveedor) {
        this.idProveedor = idProveedor;
    }
    
}
