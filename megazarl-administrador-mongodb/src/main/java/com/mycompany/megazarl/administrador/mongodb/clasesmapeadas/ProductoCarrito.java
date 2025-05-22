
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import org.bson.types.ObjectId;


public class ProductoCarrito {
    
    private ObjectId id;
    
    private Long idLong;
    
    private CarritoCompras carritoCompras;
    
    private Producto producto;
    
    private Integer cantidad;

    public ProductoCarrito() {
    }

    public ProductoCarrito(CarritoCompras carritoCompras, Producto producto, Integer cantidad) {
        this.carritoCompras = carritoCompras;
        this.producto = producto;
        this.cantidad = cantidad;
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

    public CarritoCompras getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(CarritoCompras carritoCompras) {
        this.carritoCompras = carritoCompras;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
}
