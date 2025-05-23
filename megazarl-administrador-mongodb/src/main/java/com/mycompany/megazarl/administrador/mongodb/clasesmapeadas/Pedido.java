
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import edu.student.itson.dissof.dto.datos.enumeradores.EstadoPedido;
import java.util.List;
import org.bson.types.ObjectId;


public class Pedido {
    
    private ObjectId id;
    
    private ObjectId idCliente;
    
    private EstadoPedido estado;
    
    private List<ProductoPedido> productosPedido;

    public Pedido(ObjectId idCliente, EstadoPedido estado, List<ProductoPedido> productosPedido) {
        this.idCliente = idCliente;
        this.estado = estado;
        this.productosPedido = productosPedido;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(ObjectId idCliente) {
        this.idCliente = idCliente;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
    
    public List<ProductoPedido> getProductosPedido() {
        return productosPedido;
    }

    public void setProductosPedido(List<ProductoPedido> productosPedido) {
        this.productosPedido = productosPedido;
    }   
    
}
