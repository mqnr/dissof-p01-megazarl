
package edu.student.itson.dissof.megazarl.dto.negocios;

public class ProductoPedidoDatosCompletosRelacionesDTO extends ProductoPedidoDTO{
    
    private ProductoDTO producto;
    
    private PedidoDTO pedido;
    
    public ProductoPedidoDatosCompletosRelacionesDTO(
            Long id,
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
    public Long getIdProducto() {
        return producto.getId();
    }

    @Override
    public Long getIdPedido() {
        return pedido.getId();
    }
    
    
}
