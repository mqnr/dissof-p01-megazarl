
package edu.student.itson.dissof.megazarl.dto.infraestructura;

public class ProductoPedidoDatosCompletosRelacionesDTO extends ProductoPedidoDTO{
    
    private ProductoDTO producto;
    
    private PedidoDTO pedido;
    
    public ProductoPedidoDatosCompletosRelacionesDTO(
            Long id,
            Integer cantidadRequerida,
            Integer cantidadCompleta,
            ProductoDTO producto,
            PedidoDTO pedido) {
        super(
            id, 
            cantidadRequerida,
            cantidadCompleta);
        
        this.producto = producto;     
        this.pedido = pedido;
    }
    
    public ProductoPedidoDatosCompletosRelacionesDTO(
            Integer cantidadRequerida,
            Integer cantidadCompleta,
            ProductoDTO producto,
            PedidoDTO pedido) {
        super(
            cantidadRequerida,
            cantidadCompleta);
        
        this.producto = producto;     
        this.pedido = pedido;
    }
    
    public ProductoPedidoDatosCompletosRelacionesDTO(
            Integer cantidadRequerida,
            Integer cantidadCompleta,
            ProductoDTO producto) {
        super(
            cantidadRequerida,
            cantidadCompleta);
        
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
