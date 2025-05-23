
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.dto.datos.enumeradores.EstadoPedido;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.List;

public class PedidoDTODatos{
    
    private IdEntidadGenericoDatos id;

    private EstadoPedido estado;
    
    private IdEntidadGenericoDatos idCliente;
    
    private IdEntidadGenericoDatos idPaqueteria;
    
    private List<ProductoPedidoDTODatos> productosPedido;

    public PedidoDTODatos(
            IdEntidadGenericoDatos id,
            EstadoPedido estado,
            ClienteDTODatos cliente,
            PaqueteriaDTODatos paqueteria,
            IdEntidadGenericoDatos idCliente,
            IdEntidadGenericoDatos idPaqueteria) {
        
        this.id = id;
        this.estado = estado;
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
    }
    
    public PedidoDTODatos(
            IdEntidadGenericoDatos id,
            EstadoPedido estado,
            ClienteDTODatos cliente,
            PaqueteriaDTODatos paqueteria,
            IdEntidadGenericoDatos idCliente,
            IdEntidadGenericoDatos idPaqueteria,
            List<ProductoPedidoDTODatos> productosPedido) {
        
        this.id = id;
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

    public EstadoPedido getEstado() {
        return estado;
    }

    public List<ProductoPedidoDTODatos> getProductosPedido() {
        return productosPedido;
    }
    
    public IdEntidadGenericoDatos getIdPaqueteria() {
        return idPaqueteria;
    }

    public List<ProductoPedidoDTODatos> getIdsProductosPedido() {
        return productosPedido;
    }
    
}
