
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import java.util.List;
import org.bson.types.ObjectId;


public class CarritoCompras {
    
    private ObjectId id;
    
    private Boolean esVigente;
    
    private ObjectId cliente;
    
    private ObjectId paqueteria;
    
    private List<ProductoCarrito> productoCarrito;

    public CarritoCompras(Boolean esVigente, ObjectId cliente, ObjectId paqueteria) {
        this.esVigente = esVigente;
        this.cliente = cliente;
        this.paqueteria = paqueteria;
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

    public ObjectId getCliente() {
        return cliente;
    }

    public void setCliente(ObjectId cliente) {
        this.cliente = cliente;
    }

    public ObjectId getPaqueteria() {
        return paqueteria;
    }

    public void setPaqueteria(ObjectId paqueteria) {
        this.paqueteria = paqueteria;
    }

    public List<ProductoCarrito> getProductoCarrito() {
        return productoCarrito;
    }

    public void setProductoCarrito(List<ProductoCarrito> productoCarrito) {
        this.productoCarrito = productoCarrito;
    }
    
    
    
    
    
}
