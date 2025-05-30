
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;


public class ProductoCarrito {
    
    private ObjectId id;

    private ObjectId idProducto;
    
    private Integer cantidad;

    public ProductoCarrito() {}

    public ProductoCarrito(ObjectId idProducto, Integer cantidad) {
        
        this.id = new ObjectId();
        this.cantidad = cantidad;
        this.idProducto = idProducto;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
}
