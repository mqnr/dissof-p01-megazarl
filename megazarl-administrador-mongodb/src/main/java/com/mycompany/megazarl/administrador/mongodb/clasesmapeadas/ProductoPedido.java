
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;


public class ProductoPedido {
    
    private ObjectId id;
    
    private Producto producto;
    
    private Pedido pedido;
    
    private Integer cantidad;

    public ProductoPedido(Producto producto, Pedido pedido, Integer cantidad) {
        this.producto = producto;
        this.pedido = pedido;
        this.cantidad = cantidad;
    }

    public ProductoPedido() {
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
}
