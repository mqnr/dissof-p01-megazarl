
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import java.util.List;
import org.bson.types.ObjectId;


/**
 * Clase que representa la colección de Carritos de compras en el sistema.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class CarritoCompras {
    
    private ObjectId id;
    
    private Boolean esVigente;
    
    private ObjectId idCliente;
    
    private ObjectId idPaqueteria;
    
    private List<ProductoCarrito> productosCarrito;

    public CarritoCompras() {}
    
    public CarritoCompras(
            Boolean esVigente, 
            ObjectId cliente, 
            ObjectId paqueteria,
            List<ProductoCarrito> productosCarrito) {
        
        this.esVigente = esVigente;
        this.idCliente = cliente;
        this.idPaqueteria = paqueteria;
        this.productosCarrito = productosCarrito;
    }
    
    public CarritoCompras(
            Boolean esVigente, 
            ObjectId cliente, 
            List<ProductoCarrito> productosCarrito) {
        
        this.esVigente = esVigente;
        this.idCliente = cliente;
        this.productosCarrito = productosCarrito;
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

    public ObjectId getIdPaqueteria() {
        return idPaqueteria;
    }

    public void setIdPaqueteria(ObjectId idPaqueteria) {
        this.idPaqueteria = idPaqueteria;
    }

    public void setPaqueteria(ObjectId idPaqueteria) {
        this.idPaqueteria = idPaqueteria;
    }

    public List<ProductoCarrito> getProductosCarrito() {
        return productosCarrito;
    }

    public void setProductosCarrito(List<ProductoCarrito> productosCarrito) {
        this.productosCarrito = productosCarrito;
    }

    

    
}
