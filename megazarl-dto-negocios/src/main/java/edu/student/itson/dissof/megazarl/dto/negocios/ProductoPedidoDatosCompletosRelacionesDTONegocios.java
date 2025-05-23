
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

public class ProductoPedidoDatosCompletosRelacionesDTONegocios extends ProductoPedidoDTONegocios{
    
    private ProductoDTONegocios producto;
    
    private PedidoDTONegocios pedido;
    
    public ProductoPedidoDatosCompletosRelacionesDTONegocios(
            IdEntidadGenericoNegocios id,
            Integer cantidadRequerida,
            ProductoDTONegocios producto,
            PedidoDTONegocios pedido) {
        super(
            id, 
            cantidadRequerida);
        
        this.producto = producto;     
        this.pedido = pedido;
    }
    
    public ProductoPedidoDatosCompletosRelacionesDTONegocios(
            Integer cantidadRequerida,
            ProductoDTONegocios producto,
            PedidoDTONegocios pedido) {
        super(
            cantidadRequerida);
        
        this.producto = producto;     
        this.pedido = pedido;
    }
    
    public ProductoPedidoDatosCompletosRelacionesDTONegocios(
            Integer cantidadRequerida,
            ProductoDTONegocios producto) {
        super(
            cantidadRequerida);
        
        this.producto = producto;     
    }

    public ProductoDTONegocios getProducto() {
        return producto;
    }

    public PedidoDTONegocios getPedido() {
        return pedido;
    }
    
    @Override
    public IdEntidadGenericoNegocios getIdProducto() {
        return producto.getId();
    }

    @Override
    public IdEntidadGenericoNegocios getIdPedido() {
        return pedido.getId();
    }
    
    
}
