
package com.mycompany.megazarl.administrador.mongodb.clasesmapeadas;

import edu.student.itson.dissof.dto.datos.enumeradores.EstadoPedidoDatos;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Clase que representa un pedido realizado por un cliente registrado en el sistema.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class Pedido {
    
    private ObjectId id;
    
    private EstadoPedidoDatos estado;
    
    private ObjectId idCliente;
    
    private ObjectId idPaqueteria;
    
    private List<ProductoPedido> productosPedido;

    public Pedido(){}
    
    public Pedido( 
            EstadoPedidoDatos estado,
            ObjectId idCliente,
            ObjectId idPaqueteria,
            List<ProductoPedido> productosPedido) {
        
        this.estado = estado;
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
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

    public ObjectId getIdPaqueteria() {
        return idPaqueteria;
    }

    public void setIdPaqueteria(ObjectId idPaqueteria) {
        this.idPaqueteria = idPaqueteria;
    }

    public EstadoPedidoDatos getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedidoDatos estado) {
        this.estado = estado;
    }
    
    public List<ProductoPedido> getProductosPedido() {
        return productosPedido;
    }

    public void setProductosPedido(List<ProductoPedido> productosPedido) {
        this.productosPedido = productosPedido;
    }   
    
}
