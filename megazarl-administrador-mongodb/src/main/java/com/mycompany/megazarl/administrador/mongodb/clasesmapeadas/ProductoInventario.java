
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;


public class ProductoInventario {
    
    private ObjectId id;
    
    private Producto producto;
    
    private Sucursal sucursal;

    public ProductoInventario() {
    }
    
    public ProductoInventario(Producto producto, Sucursal sucursal) {
        this.producto = producto;
        this.sucursal = sucursal;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
    
    
    
}
