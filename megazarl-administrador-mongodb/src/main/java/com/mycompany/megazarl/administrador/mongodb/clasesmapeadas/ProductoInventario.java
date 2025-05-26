
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;


public class ProductoInventario {
    
    private ObjectId id;
    
    private ObjectId idProducto;
    
    private ObjectId idSucursal;
    
    private Boolean apartado;

    public ProductoInventario() {}
    
    public ProductoInventario(
            ObjectId idProducto, 
            ObjectId idSucursal,
            Boolean apartado) {
        
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
        this.apartado = apartado;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    public ObjectId getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(ObjectId idProducto) {
        this.idProducto = idProducto;
    }

    public ObjectId getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(ObjectId idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Boolean getApartado() {
        return apartado;
    }

    public void setApartado(Boolean apartado) {
        this.apartado = apartado;
    }
    
}
