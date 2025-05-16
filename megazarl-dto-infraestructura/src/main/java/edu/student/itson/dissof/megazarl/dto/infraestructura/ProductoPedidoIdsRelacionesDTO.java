
package edu.student.itson.dissof.megazarl.dto.infraestructura;


public class ProductoPedidoIdsRelacionesDTO extends ProductoPedidoDTO{

    private Long idProducto;
    
    private Long idPedido;

    public ProductoPedidoIdsRelacionesDTO(
            Long id,
            Integer cantidadRequerida,
            Long idProducto,
            Long idPedido) {
        super(
            id, 
            cantidadRequerida);
        
        this.idProducto = idProducto;     
        this.idPedido = idPedido;
    }
    
    
    @Override
    public Long getIdProducto() {
        return idProducto;
    }

    @Override
    public Long getIdPedido() {
        return idPedido;
    }

    
}
