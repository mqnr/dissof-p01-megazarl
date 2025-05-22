
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;

public class ProductoPedidoDatosCompletosRelacionesDTO extends ProductoPedidoDTO{
    
    private ProductoDTO producto;
    
    private PedidoDTO pedido;
    
    public ProductoPedidoDatosCompletosRelacionesDTO(
            IdEntidadGenerico id,
            Integer cantidadRequerida,
            ProductoDTO producto,
            PedidoDTO pedido) {
        super(
            id, 
            cantidadRequerida);
        
        this.producto = producto;     
        this.pedido = pedido;
    }
    
    public ProductoPedidoDatosCompletosRelacionesDTO(
            Integer cantidadRequerida,
            ProductoDTO producto,
            PedidoDTO pedido) {
        super(
            cantidadRequerida);
        
        this.producto = producto;     
        this.pedido = pedido;
    }
    
    public ProductoPedidoDatosCompletosRelacionesDTO(
            Integer cantidadRequerida,
            ProductoDTO producto) {
        super(
            cantidadRequerida);
        
        this.producto = producto;     
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }
    
    @Override
    public IdEntidadGenerico getIdProducto() {
        return producto.getId();
    }

    @Override
    public IdEntidadGenerico getIdPedido() {
        return pedido.getId();
    }
    
    
}
