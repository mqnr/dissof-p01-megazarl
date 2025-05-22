
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import java.util.List;
import org.bson.types.ObjectId;


public class CarritoCompras {
    
    private ObjectId id;
    
    private Boolean esVigente;
    
    private ObjectId idCliente;
    
    private ObjectId idPaqueteria;
    
    private List<ProductoCarrito> productosCarrito;

    public CarritoCompras(Boolean esVigente, ObjectId cliente, ObjectId paqueteria) {
        this.esVigente = esVigente;
        this.idCliente = cliente;
        this.idPaqueteria = paqueteria;
    }

    public CarritoCompras() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    public Boolean getEsVigente() {
        return esVigente;
    }

    public void setEsVigente(Boolean esVigente) {
        this.esVigente = esVigente;
    }

    public ObjectId getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(ObjectId idCliente) {
        this.idCliente = idCliente;
    }

    public ObjectId getPaqueteria() {
        return idPaqueteria;
    }

    public void setPaqueteria(ObjectId idPaqueteria) {
        this.idPaqueteria = idPaqueteria;
    }

    public List<ProductoCarrito> getProductosCarrito() {
        return productosCarrito;
    }

    public void setProductoCarrito(List<ProductoCarrito> productoCarrito) {
        this.productosCarrito = productoCarrito;
    }
    
}
