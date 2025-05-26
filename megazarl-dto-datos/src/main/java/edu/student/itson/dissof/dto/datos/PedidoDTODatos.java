
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.dto.datos.enumeradores.EstadoPedidoDatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.List;

public class PedidoDTODatos{
    
    private IdEntidadGenericoDatos id;

    private EstadoPedidoDatos estado;
    
    private IdEntidadGenericoDatos idCliente;
    
    private IdEntidadGenericoDatos idPaqueteria;
    
    private List<ProductoPedidoDTODatos> productosPedido;

    public PedidoDTODatos(
            IdEntidadGenericoDatos id,
            EstadoPedidoDatos estado,
            IdEntidadGenericoDatos idCliente,
            IdEntidadGenericoDatos idPaqueteria,
            List<ProductoPedidoDTODatos> productosPedido) {
        
        this.id = id;
        this.estado = estado;
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.productosPedido = productosPedido;
    }
    
    public PedidoDTODatos(
            EstadoPedidoDatos estado,
            IdEntidadGenericoDatos idCliente,
            IdEntidadGenericoDatos idPaqueteria,
            List<ProductoPedidoDTODatos> productosPedido) {
        
        this.estado = estado;
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.productosPedido = productosPedido;
    }

    public IdEntidadGenericoDatos getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(IdEntidadGenericoDatos idCliente) {
        this.idCliente = idCliente;
    }

    public IdEntidadGenericoDatos getId() {
        return id;
    }

    public EstadoPedidoDatos getEstado() {
        return estado;
    }

    public List<ProductoPedidoDTODatos> getProductosPedido() {
        return productosPedido;
    }
    
    public IdEntidadGenericoDatos getIdPaqueteria() {
        return idPaqueteria;
    }

    
}
