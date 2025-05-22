
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import java.util.List;
import org.bson.types.ObjectId;


public class Pedido {
    
    private ObjectId id;
    
    private Cliente cliente;
    
    private List<ProductoPedido> productosPedido;

    public Pedido(Cliente cliente, List<ProductoPedido> productosPedido) {
        this.cliente = cliente;
        this.productosPedido = productosPedido;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ProductoPedido> getProductosPedido() {
        return productosPedido;
    }

    public void setProductosPedido(List<ProductoPedido> productosPedido) {
        this.productosPedido = productosPedido;
    }
    
    
}
